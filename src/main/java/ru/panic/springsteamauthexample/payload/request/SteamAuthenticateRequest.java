package ru.panic.springsteamauthexample.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SteamAuthenticateRequest(
        @JsonProperty("openid.ns")
        String openidNs,

        @JsonProperty("openid.mode")
        String openidMode,

        @JsonProperty("openid.op_endpoint")
        String openidOpEndpoint,

        @JsonProperty("openid.claimed_id")
        String openidClaimedId,

        @JsonProperty("openid.identity")
        String openidIdentity,

        @JsonProperty("openid.return_to")
        String openidReturnTo,

        @JsonProperty("openid.response_nonce")
        String openidResponseNonce,

        @JsonProperty("openid.assoc_handle")
        String openidAssocHandle,

        @JsonProperty("openid.signed")
        String openidSigned,

        @JsonProperty("openid.sig")
        String openidSig
) {
}
