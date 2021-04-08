package com.example.face.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginReq {
    private String mobile;
    private String vcode;
}
