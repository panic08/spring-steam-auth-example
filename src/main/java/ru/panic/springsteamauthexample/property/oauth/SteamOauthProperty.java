package ru.panic.springsteamauthexample.property.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("oauth.steam")
@Getter
@Setter
public class SteamOauthProperty {
    private String redirectUrl;
}
