package com.hoanghiep.reactiveprojectdemo.service.impl;

import com.hoanghiep.reactiveprojectdemo.entity.User;
import com.hoanghiep.reactiveprojectdemo.repository.UserRepository;
import com.hoanghiep.reactiveprojectdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    private final UserRepository userRepository;

    @Override
    public Flux<User> list() {
//        return userRepository.findAll();
        return null;
    }
}
