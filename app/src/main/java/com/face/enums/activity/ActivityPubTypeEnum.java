package com.face.enums.activity;

public enum ActivityPubTypeEnum {
    参与者朋友可见(0),
    朋友可见(1),
    公开(2),
    邀请可见(3),
    NONE(-1),
    ;

    public final int code;

    ActivityPubTypeEnum(int code) {
        this.code = code;
    }

    public static ActivityPubTypeEnum get(int code){
        for(ActivityPubTypeEnum e:values()){
            if (e.code == code){
                return e;
            }
        }
        return NONE;
    }
}
