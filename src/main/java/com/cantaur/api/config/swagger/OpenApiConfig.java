package com.cantaur.api.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {
    @Value("${springdoc.api-endpoint-host}")
    private String apiEndpointHost;
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("API")
                .description("연습 프로젝트 API 명세서입니다.")
                .version("v0.0.1");

        return new OpenAPI()
                .servers(Collections.singletonList(new Server().url(apiEndpointHost)))
                .info(info);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/cantaur/api/**")
                .build();
    }
}
