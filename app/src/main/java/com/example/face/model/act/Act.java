package com.example.face.model.act;

import lombok.Data;

@Data
public class Act {
    private long id;
    private long aid;
    private int status;
    private String title;
    private String address;
    private String stime;
    private Integer pubType;
}
