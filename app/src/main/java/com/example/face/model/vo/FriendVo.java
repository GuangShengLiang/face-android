package com.example.face.model.vo;

import com.example.face.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FriendVo {
    private Integer id;
    /**
     * 备注名
     */
    private String remark;
    /**
     * 好友
     */
    private Account friend;
    private String header;
}
