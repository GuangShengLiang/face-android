package com.face.http.model.vo;

import lombok.Data;

@Data
public class AccountInfoVO {
    /**
     * 账户详情
     */
    private AccountVO account;
    /**
     * 关系信息
     */
    private FriendshipVo friendship;
}
