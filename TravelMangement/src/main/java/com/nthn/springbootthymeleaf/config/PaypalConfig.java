package com.nthn.springbootthymeleaf.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {

  @Value("${paypal.client.id}")
  private String clientId;

  @Value("${paypal.client.secret}")
  private String clientSecret;

  @Value("${paypal.mode}")
  private String mode;

  @Bean
  public APIContext apiContext() {

    return new APIContext(clientId, clientSecret, mode).setConfigurationMap(paypalSdkConfig());
  }

  @Bean
  public Map<String, String> paypalSdkConfig() {

    return Map.of("mode", mode);
  }

  @Bean
  public OAuthTokenCredential oAuthTokenCredential() {

    return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
  }
}
