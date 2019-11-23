package com.heima.dao;

import com.heima.pojo.Member;

public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);


    Integer findMemberCountBeforeDate(String s);

    Integer findMember(String today);

    Integer findMemberTotalCount();

    Integer findMemberCountAfterDate(String date);
}


