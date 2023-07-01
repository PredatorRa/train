package com.miker.train.member.service;

import com.miker.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author miker
 * @create 2023-07-01 14:48
 */
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return memberMapper.count();
    }
}
