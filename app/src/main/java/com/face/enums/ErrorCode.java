package com.face.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    success(0, "成功"),
    fail(1, "失败"),
    invalidArgument(2, "参数不合法"),
    unLogin(401, "未登录"),
    serverError(500, "服务异常"),
    serverUnavailable(503, "服务不可用");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
