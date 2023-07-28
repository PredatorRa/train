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
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误"),

    BUSINESS_STATION_NAME_UNIQUE_ERROR("车站已存在"),
    BUSINESS_TRAIN_CODE_UNIQUE_ERROR("车次编号已存在"),
    BUSINESS_TRAIN_STATION_INDEX_UNIQUE_ERROR("同车次站序已存在"),
    BUSINESS_TRAIN_STATION_NAME_UNIQUE_ERROR("同车次站名已存在"),
    BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR("同车次厢号已存在"),
    CONFIRM_ORDER_TICKET_COUNT_ERROR("余票不足"),
    CONFIRM_ORDER_EXCEPTION("服务器忙，请稍候重试"),
    CONFIRM_ORDER_LOCK_FAIL("当前抢票人数多，请稍候重试"),
    CONFIRM_ORDER_FLOW_EXCEPTION("当前抢票人数多，请稍候重试"),
    ;


    private String desc;
}
