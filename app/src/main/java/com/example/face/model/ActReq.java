package com.example.face.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ActReq {
    private long aid;
    private String title;
    private String detail;
    private String address;
    private String picture;
    private Integer status;
    private Integer pubType;
    private Double latitude;
    private Double longitude;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
