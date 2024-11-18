package org.example.apigateway.routes.microservices;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class UserPaymentRoutes {
    private static final String uri="http://localhost:9091";
    private static final String id="service-usuarios";



    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return GatewayRouterFunctions.route(id+"1")
                .route(RequestPredicates.path("/usuarios"), HandlerFunctions.http(uri))
                .build();
    }



}
