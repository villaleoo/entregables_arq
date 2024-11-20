package org.example.apigateway.routes;




import jakarta.ws.rs.POST;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;



@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> authServiceRoute(){
        return GatewayRouterFunctions.route("api-gateway1")
                .route(RequestPredicates.all().and(RequestPredicates.path("/auth")),
                        HandlerFunctions.http("http://localhost:8080"))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> accountsServiceRoute(){
        return GatewayRouterFunctions.route("api-gateway2")
                .route(RequestPredicates.all().and(RequestPredicates.path("/cuentas")),
                        HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> travelsServiceRoute(){
        return GatewayRouterFunctions.route("service-gestion1")
                .route(RequestPredicates.all().and(RequestPredicates.path("/viajes/**")),
                        HandlerFunctions.http("http://localhost:9093"))
                .build();

    }

    @Bean
    public RouterFunction<ServerResponse> feeServiceRoute(){
        return GatewayRouterFunctions.route("service-gestion2")
                .route(RequestPredicates.all().and(RequestPredicates.path("/tarifas/**")),
                        HandlerFunctions.http("http://localhost:9093"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> monopatinesServiceRoute(){
        return GatewayRouterFunctions.route("service-monopatines")
                .route(RequestPredicates.all().and(RequestPredicates.path("/monopatines/**")),
                        HandlerFunctions.http("http://localhost:9090"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> stopServiceRoute(){
        return GatewayRouterFunctions.route("service-paradas")
                .route(RequestPredicates.all().and(RequestPredicates.path("/paradas/**")),
                        HandlerFunctions.http("http://localhost:9092"))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return GatewayRouterFunctions.route("service-usuarios2")
                .route(RequestPredicates.all().and(RequestPredicates.path("/usuarios/**")),
                        HandlerFunctions.http("http://localhost:9091"))
                .build();
    }

}
