package com.face.http.model.vo;

import lombok.Data;

@Data
public class AccountDetail {
    private int uid;
    private String nickName;
    private String avatar;
    private String birthday;
    private String year;
    private int status;
    private int gender;
    private String genderName;
}
