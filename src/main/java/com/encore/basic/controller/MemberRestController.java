package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
//@RestController 사용 시, @ResponseBody 안 붙여도 됨
@RequestMapping("/rest")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("member/create")
    public String save(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.save(memberRequestDto);
        return "ok";
    }

    @GetMapping("members")
    public List<MemberResponseDto> showMembers() {
        return memberService.findAll();
    }

    @GetMapping("member/find/{id}")
    public ResponseEntity<Map<String, Object>> findMember(@PathVariable int id) {
        MemberResponseDto memberResponseDto = null;
        try {
            memberResponseDto = memberService.findById(id);
            return ResponseEntityController.successResponse(HttpStatus.CREATED, memberResponseDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntityController.errResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("member/delete/{id}")
    public String deleteMember(@PathVariable int id) {
        memberService.delete(id);
        return "ok";
    }

    @PatchMapping("member/update")
    public MemberResponseDto updateMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);
        return memberService.findById(memberRequestDto.getId());
    }

}