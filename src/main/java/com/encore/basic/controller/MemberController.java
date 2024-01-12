package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @GetMapping("members")
    @ResponseBody
    public String showMembers() {
        return "member/member-list";
    }

    @GetMapping("member/create-screen")
    public String createMemberPage() {
        return "member/member-create";
    }

    @PostMapping("member/create")
    @ResponseBody
    public String createMember(Member member) {
        return "ok";
    }

}
