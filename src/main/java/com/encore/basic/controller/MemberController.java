package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@Controller
//@RequiredArgsConstructor
//Spring Bean이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전 (Inversion of Control) -> IOC 컨테이너가 Spring Bean을 관리 (빈 생성, 의존성 주입)
public class MemberController {
//    의존성 주입 (Dependency Injection) 방법 #1 : 필드 주입 방식
//    @Autowired // automatic dependency injection
//    private MemberService memberService; //여기서는 final 사용 불가 -> 생성자가 없어서 초기화가 안 됨

    //    의존성 주입 (Dependency Injection) 방법 #2 : 생성자 주입 방식, 가장 많이 사용됨
//    장점 :
//    1) final을 통해 상수로 사용 가능 -> 변수의 안정성을 높일 수 있음
//    2) 다형성 구현 가능
//    3) 순환참조방지
//    생성자가 1개밖에 없을 때에는 @Autowired 생략 가능
    private final MemberService memberService; //final을 붙여줘야함 -> 어차피 밑에 Constructor에서 초기화가 되기 때문에 괜찮음

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //  의존성 주입 (Dependency Injection) 방법 #3 : @RequiredArgsConstructor를 이용한 방식
//    @RequiredArgsConstructor : @NonNull 어노테이션이 붙어있는 필드 또는
//    초기화되지 않은 final 필드를 대상으로 생성자 생성
//    다형성 구현 불가, final 이라는 키워드를 쓰는 것밖에 장점이 없음

    @GetMapping("/")
    public String goHome() {
        return "member/header";
    }

    @GetMapping("members")
    public String showMembers(Model model) {
        model.addAttribute("memberList", memberService.findAll());
        return "member/member-list";
    }

    @GetMapping("member/create")
    public String createMemberPage() {
        return "member/member-create";
    }

    @PostMapping("member/create")
    public String save(MemberRequestDto memberRequestDto) {
//        Transaction 테스트
//        try {
//            memberService.save(memberRequestDto);
//            return "redirect:/members"; //url 리다이렉트
//        } catch (IllegalArgumentException e) {
//            return "member/404-error-page";
//        }

        memberService.save(memberRequestDto);
        return "redirect:/members"; //url 리다이렉트
    }

    @GetMapping("member/find")
    public String findMember(@RequestParam(value = "id") int id, Model model) {
        try {
            model.addAttribute("member", memberService.findById(id));
            return "member/member-detail";
        } catch (NoSuchElementException e) {
            return "member/404-error-page";
        }
    }

    @GetMapping("member/delete")
    public String deleteMember(@RequestParam(value = "id") int id) {
        memberService.delete(id);
        return "redirect:/members";
    }

    @PostMapping("member/update")
    public String updateMember(MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);
        return "redirect:/member/find?id=" + memberRequestDto.getId();
    }

}
