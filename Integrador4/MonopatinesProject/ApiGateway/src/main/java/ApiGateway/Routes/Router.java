package ApiGateway.Routes;

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
    public RouterFunction<ServerResponse> rutaViajes() {
        return GatewayRouterFunctions.route("service-viajes")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/viajes/**")),
                        HandlerFunctions.http("http://localhost:8003"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> rutaTarifas() {
        return GatewayRouterFunctions.route("service-tarifas")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/tarifas/**")),
                        HandlerFunctions.http("http://localhost:8003"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> rutaPausas() {
        return GatewayRouterFunctions.route("service-pausas")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/pausas/**")),
                        HandlerFunctions.http("http://localhost:8003"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> rutaMonopatines() {
        return GatewayRouterFunctions.route("service-monopatines")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/monopatines/**")),
                        HandlerFunctions.http("http://localhost:8004"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> rutaParadas() {
        return GatewayRouterFunctions.route("service-paradas")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/paradas/**")),
                        HandlerFunctions.http("http://localhost:8004"))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> rutaUsuarios() {
        return GatewayRouterFunctions.route("service-usuarios")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/usuarios/**")),
                        HandlerFunctions.http("http://localhost:8005"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> rutacuentasMP() {
        return GatewayRouterFunctions.route("service-cuentasmp")
                .route(RequestPredicates.all().and(RequestPredicates.path("api/cuentasmp/**")),
                        HandlerFunctions.http("http://localhost:8005"))
                .build();
    }

}