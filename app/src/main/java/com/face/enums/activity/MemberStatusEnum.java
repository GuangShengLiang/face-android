package com.face.enums.activity;

public enum MemberStatusEnum {
    正常(0),
    退出(1);

    public final int code;

    MemberStatusEnum(int code) {
        this.code = code;
    }
}
