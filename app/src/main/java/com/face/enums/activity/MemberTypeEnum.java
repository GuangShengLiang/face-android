package com.face.enums.activity;

public enum MemberTypeEnum {
    参与者(0),
    组织者(1);

    public final int code;

    MemberTypeEnum(int code) {
        this.code = code;
    }
}
