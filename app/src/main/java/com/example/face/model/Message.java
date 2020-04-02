package com.example.face.model;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private Integer uid;
    private Integer fuid;
    private Integer status;
    private String content;
    private String avatar;
    private String nickName;
}
