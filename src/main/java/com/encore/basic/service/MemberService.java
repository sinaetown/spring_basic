package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> Spring Bean으로 등록
//Spring Bean이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전 (Inversion of Control) -> IOC 컨테이너가 Spring Bean을 관리 (빈 생성, 의존성 주입)
@Service


public class MemberService {
//    @Autowired // automatic dependency injection
//    private MemoryMemberRepository memoryMemberRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.memberRepository = springDataJpaMemberRepository;
    }

    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member m : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(m.getId(), m.getName(),
                    m.getEmail(), m.getPassword(), m.getCreated_time());
//            memberResponseDto.setName(m.getName());
//            memberResponseDto.setEmail(m.getEmail());
//            memberResponseDto.setPassword(m.getPassword());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    //    Transactional 어노테이션 클래스 단위로 붙이면 모든 메서드에 각각 Transaction 적용
//    Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정
    @Transactional
    public void save(MemberRequestDto memberRequestDto) throws IllegalArgumentException {
        Member member = new Member(memberRequestDto.getName(),
                memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);

//        Transaction 테스트
//        Member member = new Member(memberRequestDto.getName(),
//                memberRequestDto.getEmail(), memberRequestDto.getPassword());
//        memberRepository.save(member);
//        if (member.getName().equals("kim")) {
//            throw new IllegalArgumentException();
//        }
    }

    public MemberResponseDto findById(int id) throws EntityNotFoundException {
        Member m = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberResponseDto memberResponseDto = new MemberResponseDto(m.getId(), m.getName(), m.getEmail(),
                m.getPassword(), m.getCreated_time());
        return memberResponseDto;
    }

    public void delete(int id) {
        Member m = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(m);
    }

    public void update(MemberRequestDto memberRequestDto) {
        Member m = memberRepository.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        m.update(memberRequestDto.getName(), memberRequestDto.getPassword());
        memberRepository.save(m);
    }
}
