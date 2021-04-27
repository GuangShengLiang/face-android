package com.face.http.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    /**
     * 用户id
     */
    private int uid;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 性别:男(1),女(2),未知(0)
     *
     */
    private int gender;
}
