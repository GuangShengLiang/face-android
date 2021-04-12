package com.example.face.model.act;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityDetail {
    private long id;
    private long aid;
    private int uid;
    private int status;
    private String title;
    private String uname;
    private String detail;
    private String address;
    private String stime;
//    private Timestamp startTime;
//    private Timestamp endTime;
}
