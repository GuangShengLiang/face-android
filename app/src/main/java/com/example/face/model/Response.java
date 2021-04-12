package com.example.face.model;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    private static Response ok = new Response();

    public static Response success() {
        return ok;
    }

    private static <T> Response<T> success(T t) {
        Response<T> r = new Response<>();
        r.setCode(0);
        r.setData(t);
        return r;
    }
}
