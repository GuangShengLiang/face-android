package com.face.http.model.param;

import lombok.Data;

@Data
public class MyInfoUpdateParam {
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 生日
     */
    private Long birthday;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 情感状态
     */
    private Integer loveStatus;
    /**
     * 简介 140字
     */
    private String brief;
    /**
     * 城市编码
     */
    private String cityCode;
}
