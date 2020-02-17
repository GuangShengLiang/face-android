package com.example.face.model;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private String uid;
    public String nickName;
    private String avatarUrl;
    private Date birthday;
    private int status;
    private int gender;
}
