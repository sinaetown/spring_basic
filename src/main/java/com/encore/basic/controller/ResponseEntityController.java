package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {

    //    <@ResponseStatus 어노테이션 방식>
    @GetMapping("responsestatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus() {
        return "OK";
    }

    @GetMapping("responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2() {
        Member member = new Member("Min", "min@gmail.com", "1111");
        return member;
    }

    //    ResponseEntity를 직접 생성한 방식 (body를 커스텀하고 싶을 때)
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1() {
        Member member = new Member("Min", "min@gmail.com", "1111");
        return new ResponseEntity<>(member, HttpStatus.CREATED); //여기 매개변수에서 Member 가 빠지면 상태코드만 알려줌 (body 없음)
    }

    //    ResponseEntity<String>일 경우, text/html로 설정 (활용도 떨어짐, 어차피 화면 주는 건 의미 없음)
    @GetMapping("custom2")
    public ResponseEntity<String> custom2() {
        String html = "<h1>없는 ID입니다.</h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

    //    Map형태의 메세지 커스텀하는 방식
    public static ResponseEntity<Map<String, Object>> errResponse(HttpStatus httpStatus, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("status message", httpStatus.getReasonPhrase());
        body.put("error message", message);
        return new ResponseEntity<>(body, httpStatus);
    }

    public static ResponseEntity<Map<String, Object>> successResponse(HttpStatus httpStatus, Object object) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("message", object);
        return new ResponseEntity<>(body, httpStatus);
    }

    //    메서드 체이닝 방식 : ResponseEntity의 클래스 메서드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1() {
        Member member = new Member("Max", "max@gmail.com", "1234");
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaining2")
    public ResponseEntity<Member> chaining2() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3() {
        Member member = new Member("Max", "max@gmail.com", "1234");
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
