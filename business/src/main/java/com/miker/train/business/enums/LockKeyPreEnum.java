package com.miker.train.business.enums;

public enum LockKeyPreEnum {

    CONFIRM_ORDER("LOCK_CONFIRM_ORDER", "订单锁"),
    SK_TOKEN("LOCK_SK_TOKEN", "令牌锁");

    private final String code;

    private final String desc;

    LockKeyPreEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
