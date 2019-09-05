package com.example.face.model;

import java.util.Date;

import lombok.Data;

@Data
public class Account {
    private String uid;
    private String nickName;
    private String avatarUrl;
    private Date birthday;
    private int status;
    private int gender;
}
