package com.face.enums.account;

import lombok.Getter;

@Getter
public enum FriendshipTypeEnum {
    friend(1),
    follow(2),
    fan(3),
    black(4),
    NONE(0);

    private final int code;

    FriendshipTypeEnum(int code) {
        this.code = code;
    }

    public static FriendshipTypeEnum valueOf(Integer code) {
        for (FriendshipTypeEnum e : values()) {
            if (Integer.valueOf(e.code).equals(code)) {
                return e;
            }
        }
        return NONE;
    }

    public boolean equals(Integer code) {
        if (code == null) {
            return false;
        }
        return code == this.code;

    }
}
