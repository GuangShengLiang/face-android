package com.face.enums.account;

public enum GenderEnum {
    男(1),
    女(2),
    未知(0);

    public final int code;

    GenderEnum(int code) {
        this.code = code;
    }
    public static GenderEnum get(int code){
        for(GenderEnum e:values()){
            if (e.code == code){
                return e;
            }
        }
        return 未知;
    }
}
