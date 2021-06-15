package com.face.http.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCreateParam {
    /**
     * 标题
     */
    private String title;
    /**
     * 详情
     */
    private String detail;
    /**
     * 地址
     */
    private String address;
    /**
     * 门牌号
     */
    private String room;

    /**
     * 隐私 类型:公开(1),粉丝(0), 朋友(2),  邀请(3)
     * 粉丝：粉丝和已报名的粉丝可见
     * 朋友：互相关注可见
     * 邀请：收到邀请的可见
     */
    private int privacyType;

    /**
     * 位置纬度
     */
    private Double latitude;

    /**
     * 位置经度
     */
    private Double longitude;

    /**
     * 活动条件限制
     */
    private Condition condition;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

    @Data
    public static class Condition {
        /**
         * 年龄限制
         */
        private Range ageRange;
        /**
         * 性别 0:不限 1：男 2：女
         */
        private int gender;
        /**
         * 情感状态 0:不限 1：单身
         */
        private int loveStatus;
        /**
         * 报名人数限制
         */
        private Range applicantNumberRange;
        /**
         * 候补人数
         */
        private int backupNumber;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Range {
        /**
         * 最小值
         */
        private int min;
        /**
         * 最大值
         */
        private int max;

    }
}
