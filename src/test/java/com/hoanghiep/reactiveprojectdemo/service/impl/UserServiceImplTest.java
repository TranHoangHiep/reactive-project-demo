package com.hoanghiep.reactiveprojectdemo.service.impl;

import com.hoanghiep.reactiveprojectdemo.repository.UserRepository;
import com.hoanghiep.reactiveprojectdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

//    @Mock
//    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
//        userService = new UserServiceImpl();
//        when(userRepository.findAll()).thenReturn(null);
    }

    @Test
    void list() {

//        assertEquals(2, userService.list().count().block());
//        assertEquals(null, userService.list());
    }
}