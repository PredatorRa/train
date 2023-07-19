package com.miker.train.business.controller;

import com.miker.train.business.req.DailyTrainTicketQueryReq;
import com.miker.train.business.resp.DailyTrainTicketQueryResp;
import com.miker.train.business.service.DailyTrainTicketService;
import com.miker.train.common.resp.CommonResp;
import com.miker.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daily-train-ticket")
public class DailyTrainTicketController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(list);
    }

}
