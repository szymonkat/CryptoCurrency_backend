package com.vaadin.clientUi;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WalletConfig {

    @Value("${back.api.address}")
    private String backApiAddress;

}
