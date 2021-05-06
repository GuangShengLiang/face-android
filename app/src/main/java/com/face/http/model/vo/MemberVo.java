package com.face.http.model.vo;

import lombok.Data;

@Data
public class MemberVo {

    /**
     * 展示名称
     *
     * @mock tom
     */
    private String displayName;

    /**
     * 关系类型：陌生人(0),朋友(1),黑名单(2)
     *
     * @mock 1
     */
    private int relationType;

    /**
     * 账户信息
     */
    private Account account;
}
