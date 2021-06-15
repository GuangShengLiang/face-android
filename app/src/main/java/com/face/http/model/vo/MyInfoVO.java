package com.face.http.model.vo;

import lombok.Data;

@Data
public class MyInfoVO {
    private int uid;
    /**
     * 称呼
     */
    private String nickName;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 生日
     */
    private long birthdayTime;
    /**
     * 年
     */
    private String year;

    private int status;
    /**
     * 性别
     */
    private int gender;
    /**
     * 感情状态
     */
    private int loveStatus;
    /**
     * 性别名称
     */
    private String genderName;
    /**
     * 感情状态名称
     */
    private String loveStatusName;
    /**
     * 关注总数
     */
    private int followTotal;
    /**
     * 粉丝总数
     */
    private int fanTotal;

}
