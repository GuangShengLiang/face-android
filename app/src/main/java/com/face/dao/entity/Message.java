package com.face.dao.entity;

import com.orm.SugarRecord;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Message extends SugarRecord {
    private String messageId;
    private String messageType;
    private String content;
    private String createTime;
    private String fromUserId;
    private String fromUserName;
    private String fromUserAvatar;
    private String toUserId;
    private String toUserName;
    private String toUserAvatar;
    private String messageBody;
    private String imageUrl;
    private int status;
    private long timestamp;

    private String targetType;
    private String groupId;

}
