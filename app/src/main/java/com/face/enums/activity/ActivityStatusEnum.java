package com.face.enums.activity;

public enum ActivityStatusEnum {
    NONE(-1),
    创建(0),
    报名中(10),
    进行中(40),
    已取消(80),
    结束(90);

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
