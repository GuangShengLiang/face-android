package com.example.face.model;

import java.util.Date;

import lombok.Data;

@Data
public class AccountReq {
    private String nickName;
    private String avatarUrl;
    private Date birthday;
    private Integer gender;
}
