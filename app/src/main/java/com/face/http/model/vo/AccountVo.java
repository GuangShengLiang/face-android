package com.face.http.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountVo extends Account {
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 状态
     */
    private int status;

}
