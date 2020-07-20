package com.lyj.gzh.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthEntity {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

}
