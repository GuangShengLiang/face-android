package com.face.http.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class RelationStatVO {
    /**
     * 关注列表
     */
    private List<FriendshipVo> followers;
    /**
     * 粉丝列表
     */
    private List<FriendshipVo> fans;
    /**
     * 关注总数
     *
     * @mock 32
     */
    private int followTotal;
    /**
     * 粉丝总数
     *
     * @mock 1024
     */
    private int fanTotal;
}
