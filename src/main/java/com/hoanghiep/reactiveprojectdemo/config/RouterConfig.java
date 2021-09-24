package com.hoanghiep.reactiveprojectdemo.config;

import com.hoanghiep.reactiveprojectdemo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    public static final String USER = "/api/v1/users";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {
        return route()
                .GET(USER, accept(APPLICATION_JSON), handler::list)
                .build();
//        return null;
    }
}
