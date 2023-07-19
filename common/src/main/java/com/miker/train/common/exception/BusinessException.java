package com.miker.train.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author miker
 * @create 2023-07-02 15:41
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException{

    private BusinessExceptionEnum e;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
