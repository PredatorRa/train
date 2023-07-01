package com.miker.train.common.controller;

import com.miker.train.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author miker
 * @create 2023-07-02 0:37
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    public final static Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp exceptionHandler(Exception e){
        CommonResp resp = new CommonResp();
        LOG.error("系统异常：",e);
        resp.setSuccess(false);
        resp.setMessage(e.getMessage());
        return resp;
    }
}
