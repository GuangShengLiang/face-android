package com.face.enums.account;

public enum FriendApplyStatus {
    已添加(1),
    申请中(0);

    public final int code;

    FriendApplyStatus(int code) {
        this.code = code;
    }
}
