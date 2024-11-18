package org.example.apigateway.routes.microservices;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class ManagementRoutes {
    private static final String uri="http://localhost:9093";
    private static final String id="service-gestion";

    @Bean
    public RouterFunction<ServerResponse> travelsServiceRoute(){
        return GatewayRouterFunctions.route(id+"1")
                .route(RequestPredicates.path("/viajes"), HandlerFunctions.http(uri))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> feeServiceRoute(){
        return GatewayRouterFunctions.route(id+"2")
                .route(RequestPredicates.path("/tarifas"),HandlerFunctions.http(uri))
                .build();
    }
}
