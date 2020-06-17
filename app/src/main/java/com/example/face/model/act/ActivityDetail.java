package com.example.face.model.act;

import lombok.Data;

@Data
public class ActivityDetail {
    private long id;
    private long aid;
    private int uid;
    private int status;
    private String title;
    private String uname;
    private String address;
    private String stime;
}
