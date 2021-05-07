package com.face.enums.account;

public enum RelationTypeEnum {
    friend(1),
    black(2),
    NONE(0);

    public final int code;

    RelationTypeEnum(int code) {
        this.code = code;
    }
    public static RelationTypeEnum valueOf(Integer code){
        for (RelationTypeEnum e:values()){
            if (Integer.valueOf(e.code).equals(code)){
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
