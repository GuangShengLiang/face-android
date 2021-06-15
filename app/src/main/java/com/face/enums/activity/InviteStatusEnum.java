package com.face.enums.activity;

public enum InviteStatusEnum {
    NONE(-1),
    已邀请(0),
    已取消(1),
    同意(2),
    婉拒(3);

    public final int code;

    InviteStatusEnum(int code) {
        this.code = code;
    }

    public static InviteStatusEnum get(int code){
        for(InviteStatusEnum e:values()){
            if (e.code == code){
                return e;
            }
        }
        return NONE;
    }
}
