package com.face.http.model.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RelationVo {
    /**
     * 关系Id
     */
    private Integer id;
    /**
     * 用户id
     */
    private int uid;
    /**
     * 关系类型
     */
    private int ruid;
    /**
     * 关系类型
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
}
