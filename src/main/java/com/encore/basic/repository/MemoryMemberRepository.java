package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private List<Member> memberDB;

    MemoryMemberRepository() {
        memberDB = new ArrayList<>();
    }

    //    List<Member>를 리턴해주는 메서드
    @Override
    public List<Member> members() {
        return memberDB;
    }

    //    Member등록 메서드
//    DTO 자체는 Service 단에서 끝남
    @Override
    public void createMember(Member member) {
        memberDB.add(member);
    }

    @Override
    public  Member findOne(int id) {
        Member answer = null;
        for (Member m : memberDB) {
            if (m.getId() == id) {
                answer = m;
                break;
            }
        }
        return answer;
    }
}
