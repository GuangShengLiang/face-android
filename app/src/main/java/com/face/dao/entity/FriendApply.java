package com.face.dao.entity;

import com.orm.SugarRecord;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FriendApply extends SugarRecord {
    private Integer uid;
    private Integer ruid;
    private Integer status;
    private String reason;
    private String nickName;
    private String avatar;
    private Long timeStamp;

}
