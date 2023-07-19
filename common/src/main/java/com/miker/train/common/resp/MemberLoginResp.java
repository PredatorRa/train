package com.miker.train.common.resp;

import lombok.Data;

@Data
public class MemberLoginResp {
    private String token;

    private Long id;

    private String mobile;

}