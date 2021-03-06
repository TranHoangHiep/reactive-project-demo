package com.hoanghiep.reactiveprojectdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "user")
@Builder
public class User {

    @Id
    private Long id;
    private String email;
    private String username;
    private String password;
}
