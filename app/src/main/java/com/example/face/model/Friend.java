package com.example.face.model;

import lombok.Data;

@Data
public class Friend {
    private Integer id;
    private Integer uid;
    private Integer ruid;
    private String remark;
    private String avatar;
    private String header;
    private String rname;
}
