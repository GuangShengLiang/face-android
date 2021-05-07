package com.face.enums.activity;

public enum ApplyStatusEnum {
    NONE(-1),
    申请中(0),
    已取消(1),
    同意(2),
    婉拒(3);

    public final int code;

    ApplyStatusEnum(int code) {
        this.code = code;
    }
    public static ApplyStatusEnum get(int code){
        for(ApplyStatusEnum e:values()){
            if (e.code == code){
                return e;
            }
        }
        return NONE;
    }

}
