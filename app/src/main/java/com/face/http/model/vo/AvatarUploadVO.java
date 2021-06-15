package com.face.http.model.vo;

import lombok.Data;

@Data
public class AvatarUploadVO {
    /**
     * 上传token
     */
    private String token;
    /**
     * 文件名
     */
    private String key;
}
