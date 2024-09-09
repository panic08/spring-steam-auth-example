package ru.panic.springsteamauthexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.panic.springsteamauthexample.api.SteamApi;
import ru.panic.springsteamauthexample.payload.request.SteamAuthenticateRequest;
import ru.panic.springsteamauthexample.payload.response.SteamAuthenticateResponse;
import ru.panic.springsteamauthexample.repository.user.UserLinkedSocialRepository;
import ru.panic.springsteamauthexample.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserLinkedSocialRepository userLinkedSocialRepository;
    private final SteamApi steamApi;

    public SteamAuthenticateResponse authenticateBySteam(SteamAuthenticateRequest request) {
        //todo
        return null;
    }
}
