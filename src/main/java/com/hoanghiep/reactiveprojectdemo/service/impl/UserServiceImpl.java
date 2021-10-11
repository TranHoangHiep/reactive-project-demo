package com.hoanghiep.reactiveprojectdemo.service.impl;

import com.hoanghiep.reactiveprojectdemo.entity.User;
import com.hoanghiep.reactiveprojectdemo.mapper.UserMapper;
import com.hoanghiep.reactiveprojectdemo.model.UserDto;
import com.hoanghiep.reactiveprojectdemo.model.UserPagedList;
import com.hoanghiep.reactiveprojectdemo.repository.UserRepository;
import com.hoanghiep.reactiveprojectdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<UserPagedList> list(PageRequest pageRequest) {
        Flux<User> userFlux = userRepository.findAll();
        return userFlux.map(userMapper::userToUserDto)
                .collect(Collectors.toList())
                .map(users -> {
                    return new UserPagedList(users, PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize()), users.size());
                });
    }

    @Override
    public Mono<UserDto> create(Mono<UserDto> userDtoMono) {
        return userDtoMono.map(userMapper::userDtoToUser)
                .flatMap(userRepository::save)
                .map(userMapper::userToUserDto);
    }
}
