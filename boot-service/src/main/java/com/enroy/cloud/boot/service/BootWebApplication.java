package com.enroy.cloud.boot.service;

import com.enroy.cloud.boot.api.biz.token.TokenConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ImportResource("classpath:boot-service.xml")
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class BootWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootWebApplication.class, args);
  }

  @Bean
  public Docket swaggerDocket() {
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).build();
  }

  @Bean(TokenConfig.BEAN_ID)
  @ConfigurationProperties(prefix = "dcommons-token")
  public TokenConfig getTokenConfig() {
    return new TokenConfig();
  }
}
