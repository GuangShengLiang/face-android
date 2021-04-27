package com.face.enums;

public enum ActivityStatusEnum {
    NONE(-1),
    报名中(0),
    报名结束(1),
    活动进行中(2),
    活动结束(3);

    public final int code;

    ActivityStatusEnum(int code) {
        this.code = code;
    }
    public static ActivityStatusEnum get(int code){
        for(ActivityStatusEnum e:values()){
            if (e.code == code){
                return e;
            }
        }
        return NONE;
    }
}
