package com.example.face.model.act;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteReq {
    private long aid;
    private List<Integer> iuid;
}
