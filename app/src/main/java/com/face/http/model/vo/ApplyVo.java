package com.face.http.model.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApplyVo {
    /**
     * 申请id
     */
    private int id;
    /**
     * 申请状态:申请中(0),已取消(1),同意(2),婉拒(3)
     */
    private Integer status;
    /**
     * 申请的活动
     */
    private ActivityVo activity;
    /**
     * 申请人
     */
    private Account applicant;
    private String statusName;
}
