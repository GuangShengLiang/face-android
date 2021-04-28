package com.face.http.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FriendApplyVo {
    /**
     * id
     */
    private Integer id;
    /**
     * 状态：申请中(0),已添加(1)
     */
    private Integer status;
    /**
     * 原因
     */
    private String reason;
    /**
     * 发送人
     */
    private Account sender;
    /**
     * 接受人
     */
    private Account receiver;
}
