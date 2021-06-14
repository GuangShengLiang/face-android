package com.face.http.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipVo {
    /**
     * 对方账户
     */
    private Account account;
    /**
     * 显示的名称 remark > friend.nickName
     * @mock 强子
     */
    private String displayName;
    /**
     * 关系类型：陌生人(0),朋友(1),黑名单(2),关注(3),粉丝(4)
     */
    private Integer relationType;
}
