package com.hoanghiep.reactiveprojectdemo.service;

import com.hoanghiep.reactiveprojectdemo.entity.User;
import com.hoanghiep.reactiveprojectdemo.model.UserPagedList;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserPagedList> list(PageRequest pageRequest);
}
