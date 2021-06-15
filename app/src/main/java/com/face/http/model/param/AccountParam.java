package com.face.http.model.param;

import java.util.Date;

import lombok.Data;

@Data
public class AccountParam {
    private String nickName;
    private String avatarUrl;
    private Date birthday;
    private Integer gender;
}
