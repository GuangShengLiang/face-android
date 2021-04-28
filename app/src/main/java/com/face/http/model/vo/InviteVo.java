package com.face.http.model.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class InviteVo {
    /**
     * 邀请id
     */
    private int id;
    /**
     * 邀请状态:已邀请(0),已取消(1),同意(2),婉拒(3)
     */
    private Integer status;
    /**
     * 邀请人
     */
    private Account inviter;
    /**
     * 被邀请人
     */
    private Account invitee;
    /**
     * 邀请参加的活动
     */
    private ActivityVo activity;
    private String statusName;

}
