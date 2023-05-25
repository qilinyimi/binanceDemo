package com.binance.demo.mybinance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 作用：RestTemplateConfig
 * </p>
 *
 * @author kyyi
 * @since 2023-05-12 10:05
 */
@Configuration
public class RestTemplateConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
