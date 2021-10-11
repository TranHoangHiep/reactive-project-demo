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

    public static final String USER_URL = "/api/v1/users";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {
        return route()
                .GET(USER_URL, accept(APPLICATION_JSON), handler::list)
                .POST(USER_URL, accept(APPLICATION_JSON), handler::create)
                .build();
//        return null;
    }
}
