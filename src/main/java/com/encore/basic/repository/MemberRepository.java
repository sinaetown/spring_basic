package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;

public interface MemberRepository {
    public List<Member> members();

    public void createMember(Member member);

    public Member findOne(int id);
}
