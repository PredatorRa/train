package com.miker.train.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author miker
 * @create 2023-07-01 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResp<T> {
    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    public CommonResp(T content) {
        this.content = content;
    }
}
