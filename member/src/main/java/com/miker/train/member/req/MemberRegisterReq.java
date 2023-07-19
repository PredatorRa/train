package com.miker.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author miker
 * @create 2023-07-01 21:53
 */
@Data
public class MemberRegisterReq {
    private Long id;

    @NotBlank(message = "【手机号】不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号码格式错误")
    private String mobile;
}
