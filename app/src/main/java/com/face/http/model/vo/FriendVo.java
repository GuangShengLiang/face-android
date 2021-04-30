package com.face.http.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FriendVo {
    private Integer id;
    /**
     * 显示的名称 remark > friend.nickName
     * @mock 强子
     */
    private String displayName;
    /**
     * 备注名
     */
    private String remark;
    /**
     * 好友
     */
    private Account friend;
    private String header;
}
