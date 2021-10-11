package com.hoanghiep.reactiveprojectdemo.handler;

import com.hoanghiep.reactiveprojectdemo.config.RouterConfig;
import com.hoanghiep.reactiveprojectdemo.model.UserDto;
import com.hoanghiep.reactiveprojectdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHandler {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final UserService userService;

    public Mono<ServerResponse> list(ServerRequest request) {
        Optional<String> optionalPageNumber = request.queryParam("pageNumber");
        Optional<String> optionalPageSize = request.queryParam("pageSize");
        Integer pageNumber = (optionalPageNumber.isPresent()) ? Integer.parseInt(optionalPageNumber.get()) : DEFAULT_PAGE_NUMBER;
        Integer pageSize = (optionalPageSize.isPresent()) ? Integer.parseInt(optionalPageSize.get()) : DEFAULT_PAGE_SIZE;
        return userService.list(PageRequest.of(pageNumber, pageSize))
                .flatMap(userDto -> {
                    return ServerResponse.ok().bodyValue(userDto);
                }).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<UserDto> userDtoMono = request.bodyToMono(UserDto.class);

        return userService.create(userDtoMono).flatMap(userDto -> {
            return ServerResponse.ok()
                    .header("location", RouterConfig.USER_URL + "/" + userDto.getId())
                    .build();
        });
    }
}
