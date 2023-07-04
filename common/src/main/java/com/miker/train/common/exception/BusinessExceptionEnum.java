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
    MEMBER_MOBILE_EXIST("手机号已注册"),
    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误");

    private String desc;
}
