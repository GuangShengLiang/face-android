package com.face.http.model;

import lombok.Data;

@Data
public class JsonResponse<T> {
    private int code;
    private String msg;
    private T data;
}
