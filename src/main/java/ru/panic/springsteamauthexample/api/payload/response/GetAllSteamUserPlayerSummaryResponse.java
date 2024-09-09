package ru.panic.springsteamauthexample.api.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

public record GetAllSteamUserPlayerSummaryResponse(Response response) {
    public record Response(List<Players> players) {
        @JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
        public record Players(
                String steamId,
                int communityVisibilityState,
                int profileState,
                String personaName,
                int commentPermission,
                String profileUrl,
                String avatar,
                String avatarMedium,
                String avatarFull,
                String avatarHash,
                long lastLogoff,
                int personaState,
                String primaryClanId,
                long timeCreated,
                int personaStateFlags,
                String locCountryCode,
                String locStateCode,
                int locCityId
        ) {}
    }
}
