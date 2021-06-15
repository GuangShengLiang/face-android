package com.face.enums.activity;

public enum ActivityPrivacyTypeEnum {
    公开(0),
    粉丝可见(10),
    朋友可见(40),
    邀请可见(80),
    NONE(-1),
    ;

    public final int code;

    ActivityPrivacyTypeEnum(int code) {
        this.code = code;
    }

    public static ActivityPrivacyTypeEnum get(int code){
        for(ActivityPrivacyTypeEnum e:values()){
            if (e.code == code){
                return e;
            }
        }
        return NONE;
    }
}
