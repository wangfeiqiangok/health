package com.heima.service;

import com.heima.pojo.Member;

import java.util.Map;

public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);

    Map<String, Object> getMemberReport();
}
