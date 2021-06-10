package com.face.http.model.param;

import lombok.Data;

@Data
public class ApplySwitchParam {
    private long aid;
    /**
     * 报名开关
     */
    private boolean applySwitch;
}
