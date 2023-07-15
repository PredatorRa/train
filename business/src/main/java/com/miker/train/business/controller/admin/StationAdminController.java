package com.miker.train.business.controller;

import com.miker.train.common.context.LoginbusinessContext;
import com.miker.train.common.resp.CommonResp;
import com.miker.train.common.resp.PageResp;
import com.miker.train.business.req.StationQueryReq;
import com.miker.train.business.req.StationSaveReq;
import com.miker.train.business.resp.StationQueryResp;
import com.miker.train.business.service.StationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody StationSaveReq req) {
        stationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> list = stationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        stationService.delete(id);
        return new CommonResp<>();
    }

}
