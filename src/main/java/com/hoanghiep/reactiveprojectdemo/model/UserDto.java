package com.hoanghiep.reactiveprojectdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Null
    private Long id;
    @NotBlank
    @Min(4)
    @Max(255)
    private String email;
    @NotBlank
    @Min(value = 8, message = "username must at leas 8 character")
    @Max(255)
    private String username;
    @NotBlank
    private String password;
}
