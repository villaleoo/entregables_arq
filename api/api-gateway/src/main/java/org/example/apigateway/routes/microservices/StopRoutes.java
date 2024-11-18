package org.example.apigateway.routes.microservices;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class StopRoutes {
    private static final String uri="http://localhost:9092";
    private static final String id="service-paradas";
    @Bean
    public RouterFunction<ServerResponse> stopServiceRoute(){
        return GatewayRouterFunctions.route(id)
                .route(RequestPredicates.path("/paradas"), HandlerFunctions.http(uri))
                .build();
    }
}
