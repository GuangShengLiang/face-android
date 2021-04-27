package com.face.http.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParam {
    private String title;
    private String address;
    private String detail;
    private String picture;
    private Integer status;
    private Integer pubType;
    private Double latitude;
    private Double longitude;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;
}
