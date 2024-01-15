package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> Spring Bean으로 등록
@Service
//Spring Bean이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전 (Inversion of Control) -> IOC 컨테이너가 Spring Bean을 관리 (빈 생성, 의존성 주입)
public class MemberService {
//    @Autowired // automatic dependency injection
//    private MemoryMemberRepository memoryMemberRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository) {
        this.memberRepository = memoryMemberRepository;
    }

    static int total_id;

    public List<MemberResponseDto> members() {
        List<Member> members = memberRepository.members();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member m : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(m.getId(), m.getName(), m.getEmail(), m.getPassword());
//            memberResponseDto.setName(m.getName());
//            memberResponseDto.setEmail(m.getEmail());
//            memberResponseDto.setPassword(m.getPassword());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    public void createMember(MemberRequestDto memberRequestDto) {
        LocalDateTime now = LocalDateTime.now();
        total_id++;
        Member member = new Member(total_id, memberRequestDto.getName(),
                memberRequestDto.getEmail(), memberRequestDto.getPassword(), now);
        memberRepository.createMember(member);
    }

    public MemberResponseDto findOne(int id) {
        Member m = memberRepository.findOne(id);
        MemberResponseDto memberResponseDto = new MemberResponseDto(m.getId(), m.getName(), m.getEmail(), m.getPassword());
        return memberResponseDto;
    }
}
