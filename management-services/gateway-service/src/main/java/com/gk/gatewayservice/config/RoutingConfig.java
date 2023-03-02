package com.gk.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("userId", r->r.path("/user/**").uri("http://localhost:8081")) //static routing
                .route("productId", r->r.path("/product/**").uri("lb://PRODUCT-SERVICE")) //dynamic routing
                .route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE")) //dynamic routing
                .build();
    }
}
