package com.miker.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author miker
 * @create 2023-07-01 21:53
 */
@Data
public class MemberRegisterReq {
    private Long id;

    @NotBlank(message = "【手机号】不能为空")
    private String mobile;
}
