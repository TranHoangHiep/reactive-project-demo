package com.hoanghiep.reactiveprojectdemo.service;

import com.hoanghiep.reactiveprojectdemo.entity.User;
import reactor.core.publisher.Flux;

public interface UserService {
    Flux<User> list();
}
