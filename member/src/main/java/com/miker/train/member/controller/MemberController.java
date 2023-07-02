package com.miker.train.member.controller;

import com.miker.train.common.resp.CommonResp;
import com.miker.train.member.req.MemberRegisterReq;
import com.miker.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miker
 * @create 2023-07-01 14:51
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count(){
        return new CommonResp<>(memberService.count());
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        return new CommonResp<>(memberService.register(req));
    }

}
