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
     */
    private List<MemberVo> friends;
    /**
     * 参与人列表
     */
    private List<MemberVo> members;
    /**
     * 状态:  创建(0), 报名中(10), 进行中(40), 已取消(80), 结束(90);
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
     *
     * @mock 04/29 08:00-23:00
     */
    private String period;
}
