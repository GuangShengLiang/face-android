package com.example.face.model;

import lombok.Data;

@Data
public class Account {
    private int uid;
    private String nickName;
    private String avatar;
    private String birthday;
    private String year;
    private int status;
    private int gender;
    private String genderName;
}
