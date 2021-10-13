package com.hoanghiep.reactiveprojectdemo.handler;

import com.hoanghiep.reactiveprojectdemo.config.RouterConfig;
import com.hoanghiep.reactiveprojectdemo.model.UserDto;
import com.hoanghiep.reactiveprojectdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHandler {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final UserService userService;
    private final Validator validator;

    public Mono<ServerResponse> list(ServerRequest request) {
        Optional<String> optionalPageNumber = request.queryParam("pageNumber");
        Optional<String> optionalPageSize = request.queryParam("pageSize");
        Integer pageNumber = (optionalPageNumber.isPresent()) ? Integer.parseInt(optionalPageNumber.get()) : DEFAULT_PAGE_NUMBER;
        Integer pageSize = (optionalPageSize.isPresent()) ? Integer.parseInt(optionalPageSize.get()) : DEFAULT_PAGE_SIZE;
        return userService.list(PageRequest.of(pageNumber, pageSize))
                .flatMap(userDto -> ServerResponse.ok().bodyValue(userDto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<UserDto> userDtoMono = request.bodyToMono(UserDto.class).doOnNext(this::validate);

        return userService.create(userDtoMono)
                .flatMap(
                        userDto -> ServerResponse.ok()
                                .header("location", RouterConfig.USER_URL + "/" + userDto.getId())
                                .build()
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<UserDto> userDtoMono = request.bodyToMono(UserDto.class);
        return userDtoMono.flatMap(
                        userDto -> userService.update(Long.parseLong(request.pathVariable("id")), userDto)
                )
                .flatMap(userDtoUpdated -> {
                    log.info("Save user id: {}", userDtoUpdated.getId());
                    return ServerResponse.ok().build();
                });
    }

    private void validate(UserDto userDto) {
        log.info("validate save");
        Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
        validator.validate(userDto, errors);

        if (errors.hasErrors()) {
            log.info("Have error");
            throw new ServerWebInputException(errors.toString());
        }
    }
}
