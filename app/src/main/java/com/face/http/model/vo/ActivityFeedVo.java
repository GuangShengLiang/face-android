package com.face.http.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActivityFeedVo extends ActivityVo {
    private int id;
    /**
     * 发布人
     */
    private MemberVo publisher;
    /**
     * 参加的好友列表
     * @deprecated
     */
    private List<MemberVo> friends;
    /**
     * 参与人列表
     */
    private List<MemberVo> members;
    /**
     * 状态:报名中(0),报名结束(1),活动进行中(2),活动结束(3)
     */
    private Integer status;
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;

    /**
     * 活动时间段
     * @mock 04/29 08:00-23:00
     */
    private String period;
}
