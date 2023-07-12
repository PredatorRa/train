package com.miker.train.member.controller;

import com.miker.train.common.context.LoginMemberContext;
import com.miker.train.common.resp.CommonResp;
import com.miker.train.common.resp.PageResp;
import com.miker.train.member.req.PassengerQueryReq;
import com.miker.train.member.req.PassengerSaveReq;
import com.miker.train.member.resp.PassengerQueryResp;
import com.miker.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author miker
 * @create 2023-07-01 14:51
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;


    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> passengerQueryResps = passengerService.queryList(req);
        return new CommonResp<>(passengerQueryResps);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        passengerService.delete(id);
        return new CommonResp<>();
    }

}
