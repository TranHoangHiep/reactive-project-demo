package com.hoanghiep.reactiveprojectdemo.repository;

import com.hoanghiep.reactiveprojectdemo.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
