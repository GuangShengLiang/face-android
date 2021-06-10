package com.face.http.model.vo;

import lombok.Data;

@Data
public class RelationStatVO {
    /**
     * 关注总数
     * @mock 32
     */
    private int followTotal;
    /**
     * 粉丝总数
     * @mock 1024
     */
    private int fanTotal;
}
