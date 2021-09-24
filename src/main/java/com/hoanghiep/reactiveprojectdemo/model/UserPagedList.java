package com.hoanghiep.reactiveprojectdemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserPagedList extends PageImpl<UserDto> implements Serializable {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserPagedList(
            @JsonProperty("content") List<UserDto> content,
            @JsonProperty("pageable") Pageable pageable,
            @JsonProperty("totalElements") long totalElements) {
        super(content, pageable, totalElements);
    }

}
