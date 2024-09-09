package ru.panic.springsteamauthexample.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panic.springsteamauthexample.payload.request.SteamAuthenticateRequest;
import ru.panic.springsteamauthexample.payload.response.SteamAuthenticateResponse;
import ru.panic.springsteamauthexample.property.oauth.SteamOauthProperty;
import ru.panic.springsteamauthexample.service.AuthService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SteamOauthProperty steamOauthProperty;
    private String OAUTH_STEAM_URL = "https://steamcommunity.com/openid/login?openid.ns=http://specs.openid.net/auth/2.0&openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select" +
            "&openid.identity=http://specs.openid.net/auth/2.0/identifier_select&openid.return_to=%s" +
            "&openid.realm=%s" +
            "&openid.mode=checkid_setup";

    @PostConstruct
    public void init() {
        OAUTH_STEAM_URL = String.format(OAUTH_STEAM_URL, steamOauthProperty.getRedirectUrl(), steamOauthProperty.getRedirectUrl());
    }

    @GetMapping("/steam")
    public ResponseEntity<Void> redirectToSteam() {
        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(URI.create(OAUTH_STEAM_URL));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/steam/authenticate")
    public ResponseEntity<SteamAuthenticateResponse> authenticateBySteam(@RequestBody SteamAuthenticateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.authenticateBySteam(request));
    }
}
