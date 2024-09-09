package ru.panic.springsteamauthexample.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("api.steam")
@Getter
@Setter
public class SteamApiProperty {
    private String clientSecret;
}
