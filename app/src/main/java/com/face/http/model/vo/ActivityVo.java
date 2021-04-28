package com.face.http.model.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ActivityVo {
    /**
     *活动ID
     */
    private long aid;
    /**
     *活动标题
     */
    private String title;
    /**
     *活动地址
     */
    private String address;
    /**
     * 活动图片
     */
    private String picture;
}
