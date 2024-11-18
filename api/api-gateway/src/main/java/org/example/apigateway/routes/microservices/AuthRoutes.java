package org.example.apigateway.routes.microservices;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class AuthRoutes {
    private static final String uri="http://localhost:8080";
    private static final String id="api-gateway";
    @Bean
    public RouterFunction<ServerResponse> authServiceRoute(){
        return GatewayRouterFunctions.route(id)
                .route(RequestPredicates.path("/auth"), HandlerFunctions.http(uri))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> accountsServiceRoute(){
        return GatewayRouterFunctions.route(id+"1")
                .route(RequestPredicates.path("/cuentas"),HandlerFunctions.http(uri))
                .build();
    }
}
