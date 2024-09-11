package ru.panic.springsteamauthexample.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.panic.springsteamauthexample.api.payload.response.GetAllSteamUserPlayerSummaryResponse;
import ru.panic.springsteamauthexample.payload.request.SteamAuthenticateRequest;
import ru.panic.springsteamauthexample.property.SteamApiProperty;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SteamApi {

    private final String STEAM_URL = "https://steamcommunity.com";
    private final String STEAM_API_URL = "https://api.steampowered.com";
    private final SteamApiProperty steamApiProperty;
    private final RestTemplate restTemplate;

    public GetAllSteamUserPlayerSummaryResponse getAllSteamUserPlayerSummaryBySteamIds(List<String> steamIds) {
        ResponseEntity<GetAllSteamUserPlayerSummaryResponse> response =
                restTemplate.getForEntity(STEAM_API_URL + "/ISteamUser/GetPlayerSummaries/v2?key=" + steamApiProperty.getClientSecret()
                + "&steamids=" + String.join(",", steamIds),
                        GetAllSteamUserPlayerSummaryResponse.class);

        if (response.getBody() == null) {
            log.warn("null response in getAllSteamUserPlayerSummaryBySteamIds in SteamApi");
        }

        return response.getBody();
    }

    public boolean checkAuthentication(SteamAuthenticateRequest steamAuthenticateRequest) {
        ResponseEntity<String> response = restTemplate.getForEntity(buildCheckAuthenticationUrl(steamAuthenticateRequest),
                String.class);

        if (response.getBody() == null) {
            log.warn("null response in checkAuthentication in SteamApi");
        }

        System.out.println(response.getBody());

        //Check that Steam didn't give us a full html page with validation error and that the data is valid
        return response.getBody().contains("true");
    }

    private String buildCheckAuthenticationUrl(SteamAuthenticateRequest steamAuthenticateRequest) {
        return STEAM_URL + "/openid/login?openid.ns=" + steamAuthenticateRequest.openidNs()
                + "&openid.mode=check_authentication"
                + "&openid.op_endpoint=" + steamAuthenticateRequest.openidOpEndpoint()
                + "&openid.claimed_id=" + steamAuthenticateRequest.openidClaimedId()
                + "&openid.identity=" + steamAuthenticateRequest.openidIdentity()
                + "&openid.return_to=" + steamAuthenticateRequest.openidReturnTo()
                + "&openid.response_nonce=" + steamAuthenticateRequest.openidResponseNonce()
                + "&openid.assoc_handle=" + steamAuthenticateRequest.openidAssocHandle()
                + "&openid.signed=" + steamAuthenticateRequest.openidSigned()
                + "&openid.sig=" + steamAuthenticateRequest.openidSig();
    }
}
