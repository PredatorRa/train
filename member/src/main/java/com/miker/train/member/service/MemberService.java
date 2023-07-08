package com.miker.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.miker.train.common.exception.BusinessException;
import com.miker.train.common.exception.BusinessExceptionEnum;
import com.miker.train.common.util.JwtUtil;
import com.miker.train.common.util.SnowUtil;
import com.miker.train.member.domain.Member;
import com.miker.train.member.domain.MemberExample;
import com.miker.train.member.mapper.MemberMapper;
import com.miker.train.member.req.MemberLoginReq;
import com.miker.train.member.req.MemberRegisterReq;
import com.miker.train.member.req.MemberSendCodeReq;
import com.miker.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author miker
 * @create 2023-07-01 14:48
 */
@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req){
        String phone = req.getMobile();
        Member memberDB = selectMemberByMobile(phone);
        if(ObjectUtil.isNotNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(phone);
        memberMapper.insert(member);

        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req){
        String phone = req.getMobile();
        Member memberDB = selectMemberByMobile(phone);

        if(ObjectUtil.isNull(memberDB)){
            LOG.info("手机号未注册");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(phone);
            memberMapper.insert(member);
        }else{
            LOG.info("手机号已注册");
        }

//        String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成校验码：{}",code);

        //手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("校验码保存至数据库");

        LOG.info("校验码服务联动，发送短信");

    }

    /**
     * 登录
     * @param req
     * @return
     */
    public MemberLoginResp login(MemberLoginReq req){
        String phone = req.getMobile();
        String code = req.getCode();

        Member membersDB = selectMemberByMobile(phone);

        //如果手机号不存在，则插入一条记录
        if(ObjectUtil.isNull(membersDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        //校验短信验证码
        if(!"8888".equals(code)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(membersDB, MemberLoginResp.class);
        Map<String, Object> map = BeanUtil.beanToMap(memberLoginResp);
        String token = JwtUtil.createToken(map);
        memberLoginResp.setToken(token);

        return memberLoginResp;
    }

    private Member selectMemberByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollectionUtil.isEmpty(members)){
            return null;
        }else{
            return members.get(0);
        }
    }
}
