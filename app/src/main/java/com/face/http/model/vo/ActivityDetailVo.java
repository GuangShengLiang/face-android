package com.face.http.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActivityDetailVo extends ActivityVo {
    private int id;
    /**
     * 发布人
     */
    private Account publisher;
    /**
     * 详情
     */
    private String detail;
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
     * 开始时间
     */
    private String stime;
    /**
     * 结束时间
     */
    private String etime;

}
