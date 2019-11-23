package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.heima.dao.MemberDao;
import com.heima.pojo.Member;
import com.heima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member findByTelephone(String telephone) {

        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    @Override
    public Map<String, Object> getMemberReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        List<String> months=new ArrayList<String>();
        List<Integer> memberCounts=new ArrayList<Integer>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String month="";
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH,1);
//            calendar.add(Calendar.DAY_OF_MONTH,-1);
            month=sdf.format(calendar.getTime());
            months.add(month);
            memberCounts.add(memberDao.findMemberCountBeforeDate(month+"-31"));
        }
        Map<String,Object> hm = new HashMap();
        hm.put("months",months);
        hm.put("memberCount",memberCounts);

        return hm;
    }
}
