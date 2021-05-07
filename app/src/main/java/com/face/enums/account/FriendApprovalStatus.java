package com.face.enums.account;

public enum FriendApprovalStatus {
    同意(1),
    申请中(0),
    NONE(-1);

    public final int code;

    FriendApprovalStatus(int code) {
        this.code = code;
    }

    public static FriendApprovalStatus valuesOf(Integer code) {
        for (FriendApprovalStatus e : values()) {
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
