package com.example.face.model.act;

import lombok.Data;

import java.util.List;

@Data
public class InviteReq {
    private long aid;
    private List<Integer> iuid;
}