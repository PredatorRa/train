package com.miker.train.common.exception;

import lombok.*;

/**
 * @author miker
 * @create 2023-07-02 15:32
 */
@Getter
@ToString
@AllArgsConstructor
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已注册");

    private String desc;
}
