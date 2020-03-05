package com.example.face.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Relation {
    private Integer id;
    private Integer uid;
    private Integer ruid;
    private int type;
    private String remark;
}
