package com.face.dao.entity;

import com.orm.SugarRecord;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends SugarRecord {
    private String userId;
    private String userWxId;
    private String userNickName;
    private String userPhone;
    private String userPassword;
    private String userImPassword;
    private String userAvatar;
    private String userHeader;
    private String userSex;
    private String userSign;
    private String userQrCode;
    private String isFriend;

    private List<User> friendList;

    private String userLastestCirclePhotos;

    private String friendSource;

    // 好友相关
    private String userFriendPhone;
    private String userFriendRemark;
    private String userFriendDesc;

    private String isStarFriend;

}
