package com.miker.train.member.service;

import cn.hutool.core.collection.CollectionUtil;
import com.miker.train.common.exception.BusinessException;
import com.miker.train.common.exception.BusinessExceptionEnum;
import com.miker.train.member.domain.Member;
import com.miker.train.member.domain.MemberExample;
import com.miker.train.member.mapper.MemberMapper;
import com.miker.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miker
 * @create 2023-07-01 14:48
 */
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req){
        String phone = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(phone);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollectionUtil.isNotEmpty(members)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(phone);
        memberMapper.insert(member);

        return member.getId();
    }
}
