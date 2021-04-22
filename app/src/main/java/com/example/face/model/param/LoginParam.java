package com.example.face.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginParam {
    private String mobile;
    private String vcode;
}
