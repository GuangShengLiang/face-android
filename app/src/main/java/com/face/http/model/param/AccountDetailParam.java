package com.face.http.model.param;

import lombok.Data;

@Data
public class AccountDetailParam {
    /**
     * 简介 140字
     */
    private String brief;
    /**
     * 城市编码
     */
    private String cityCode;
}
