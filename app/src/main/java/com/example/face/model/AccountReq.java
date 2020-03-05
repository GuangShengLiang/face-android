package com.example.face.model;

import lombok.Data;

import java.util.Date;

@Data
public class AccountReq {
    private String nickName;
    private String avatarUrl;
    private Date birthday;
    private Integer gender;
}
