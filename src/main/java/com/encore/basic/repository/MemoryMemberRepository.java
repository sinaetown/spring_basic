package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private List<Member> memberDB;
    static int total_id;

    MemoryMemberRepository() {
        memberDB = new ArrayList<>();
    }

    //    List<Member>를 리턴해주는 메서드
    @Override
    public List<Member> findAll() {
        return memberDB;
    }

    //    Member등록 메서드
//    DTO 자체는 Service 단에서 끝남
    @Override
    public Member save(Member member) {
        total_id++;
        LocalDateTime now = LocalDateTime.now();
        member.setId(total_id);
        member.setCreated_time(now);
        memberDB.add(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        for (Member m : memberDB) {
            if (m.getId() == id) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Member member) {

    }

}
