package com.hoanghiep.reactiveprojectdemo.controller;

import com.hoanghiep.reactiveprojectdemo.model.UserPagedList;
import com.hoanghiep.reactiveprojectdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<Mono<UserPagedList>> list(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return ResponseEntity.ok(userService.list(PageRequest.of(pageNumber, pageSize)));
    }
}
