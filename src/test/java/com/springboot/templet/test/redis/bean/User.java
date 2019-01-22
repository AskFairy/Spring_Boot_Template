package com.springboot.templet.test.redis.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private String username;
    private Integer age;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
