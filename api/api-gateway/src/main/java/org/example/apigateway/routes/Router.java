package org.example.apigateway.routes;


import org.example.apigateway.routes.microservices.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({
        AuthRoutes.class,
        UserPaymentRoutes.class,
        ManagementRoutes.class,
        MonopatinRoutes.class,
        StopRoutes.class,
})
public class Router { }
