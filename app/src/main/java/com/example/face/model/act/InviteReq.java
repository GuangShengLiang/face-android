package com.example.face.model.act;

import java.util.List;

import lombok.Data;

@Data
public class InviteReq {
    private long aid;
    private List<Integer> iuid;
}
