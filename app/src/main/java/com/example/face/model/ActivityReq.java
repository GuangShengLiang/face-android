package com.example.face.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityReq {
    private String title;
    private String time;
    private String address;
    private String content;
}
