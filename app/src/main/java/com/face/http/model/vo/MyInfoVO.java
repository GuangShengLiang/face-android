package com.face.http.model.vo;

import lombok.Data;

@Data
public class MyInfoVO {
    private int uid;
    private String nickName;
    private String avatar;
    private long birthdayTime;
    private String year;
    private int status;
    private int gender;
    private int loveStatus;
    private String genderName;
    private String loveStatusName;
}
