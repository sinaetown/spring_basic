package com.encore.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

//보통 Getter만 사용
@Getter
@AllArgsConstructor //모든 매개변수를 넣은 생성자 (기본 생성자 없어짐)
//@NoArgsConstructor -> 기본 생성자 만들어줌
public class Member {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created_time;
}
