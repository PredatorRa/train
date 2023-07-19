package com.miker.train.common.controller;

import com.miker.train.common.exception.BusinessException;
import com.miker.train.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
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
        resp.setMessage("系统出现异常，请联系管理员");
        return resp;
    }

    /**
     * 业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BusinessException e){
        CommonResp resp = new CommonResp();
        LOG.error("业务异常:{}",e.getE().getDesc());
        resp.setSuccess(false);
        resp.setMessage(e.getE().getDesc());
        return resp;
    }

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BindException e){
        CommonResp resp = new CommonResp();
        LOG.error("业务异常:{}",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        resp.setSuccess(false);
        resp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return resp;
    }
}
