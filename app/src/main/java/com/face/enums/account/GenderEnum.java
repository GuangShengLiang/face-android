package com.face.enums.account;

import lombok.Getter;

@Getter
public enum GenderEnum {
    man(0, "男"),
    female(1, "女"),
    unkonw(-1, "");

    private final int code;
    private final String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GenderEnum get(int code) {
        for (GenderEnum e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return unkonw;
    }
}
