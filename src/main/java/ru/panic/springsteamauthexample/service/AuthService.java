package ru.panic.springsteamauthexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.panic.springsteamauthexample.api.SteamApi;
import ru.panic.springsteamauthexample.api.payload.response.GetAllSteamUserPlayerSummaryResponse;
import ru.panic.springsteamauthexample.dto.UserDto;
import ru.panic.springsteamauthexample.exception.AuthException;
import ru.panic.springsteamauthexample.mapper.UserToUserDtoMapperImpl;
import ru.panic.springsteamauthexample.model.User;
import ru.panic.springsteamauthexample.model.UserLinkedSocial;
import ru.panic.springsteamauthexample.model.enums.UserLinkedSocialType;
import ru.panic.springsteamauthexample.payload.request.SteamAuthenticateRequest;
import ru.panic.springsteamauthexample.payload.response.SteamAuthenticateResponse;
import ru.panic.springsteamauthexample.repository.user.UserLinkedSocialRepository;
import ru.panic.springsteamauthexample.repository.user.UserRepository;
import ru.panic.springsteamauthexample.security.UserDetails;
import ru.panic.springsteamauthexample.security.jwt.JwtUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserLinkedSocialRepository userLinkedSocialRepository;
    private final SteamApi steamApi;
    private final UserToUserDtoMapperImpl userToUserDtoMapper;

    public UserDto getAuthUser(UUID userId) {
        return userToUserDtoMapper.userToUserDto(userRepository.findById(userId).orElseThrow());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public SteamAuthenticateResponse authenticateBySteam(SteamAuthenticateRequest request) {
        boolean isCorrectAuthentication = steamApi.checkAuthentication(request);

        if (!isCorrectAuthentication) {
            throw new AuthException("Unpredictable error during validation attempt");
        }

        // Substring string of the type "https://steamcommunity.com/openid/id/{steamId}"
        String currentUserSteamId = request.openidIdentity().substring(37);

        UserLinkedSocial currentUserLinkedSocial = userLinkedSocialRepository
                .findByVerificationDataAndSocialType(currentUserSteamId, UserLinkedSocialType.STEAM);

        System.out.println(currentUserLinkedSocial);

        // if user already registered
        if (currentUserLinkedSocial != null) {
            String authToken = jwtUtil.generateAccessToken(new UserDetails(currentUserLinkedSocial.getUserId().toString()));

            return new SteamAuthenticateResponse(authToken);
        } else {
            GetAllSteamUserPlayerSummaryResponse.Response.Players steamPlayerSummary = steamApi
                    .getAllSteamUserPlayerSummaryBySteamIds(List.of(currentUserSteamId)).response().players().get(0);

            User newUser = User.builder()
                    .username(steamPlayerSummary.personaName())
                    .registeredAt(new Date())
                    .build();

            newUser = userRepository.save(newUser);

            UserLinkedSocial userLinkedSocial = UserLinkedSocial.builder()
                    .userId(newUser.getId())
                    .socialType(UserLinkedSocialType.STEAM)
                    .verificationData(currentUserSteamId)
                    .build();

            userLinkedSocialRepository.save(userLinkedSocial);

            String authToken = jwtUtil.generateAccessToken(new UserDetails(newUser.getId().toString()));

            return new SteamAuthenticateResponse(authToken);
        }
    }
}
