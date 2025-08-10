package com.program.springpost.dto;

import com.program.springpost.domain.User;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

public class AuthorDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDto() {
    }

    public AuthorDto(User user) {
        BeanUtils.copyProperties(user,this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
