package com.miker.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author miker
 * @create 2023-07-02 17:36
 */
public class SnowUtil {
    private final static long WORKER_ID = 1;
    private final static long DATACENTER_ID = 1;

    public static Long getSnowflakeNextId(){
        return IdUtil.getSnowflake(WORKER_ID,DATACENTER_ID).nextId();
    }

    public static String getSnowflakeNextIdStr(){
        return IdUtil.getSnowflake(WORKER_ID,DATACENTER_ID).nextIdStr();
    }
}
