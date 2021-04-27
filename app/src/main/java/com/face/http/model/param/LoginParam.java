package com.face.http.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginParam {
    private String mobile;
    private String vcode;
}
