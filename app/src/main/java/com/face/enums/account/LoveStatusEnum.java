package com.face.enums.account;

import lombok.Getter;

@Getter
public enum LoveStatusEnum {
    single(0, "单身"),
    dating(1, "恋爱中"),
    married(2, "已婚"),
    unkonw(-1, "");

    private final int code;
    private final String desc;

    LoveStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static LoveStatusEnum get(int code) {
        for (LoveStatusEnum e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return unkonw;
    }

}
