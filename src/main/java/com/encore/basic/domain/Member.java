package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

//보통 Getter만 사용
@Getter
@NoArgsConstructor
//@AllArgsConstructor //모든 매개변수를 넣은 생성자 (기본 생성자 없어짐)
//@NoArgsConstructor -> 기본 생성자 만들어줌
//Entity 어노테이션을 통해 mariaDB의 테이블 및 컬럼을 자동 생성
//Class명은 테이블명, 변수명은 컬럼명
@Entity
public class Member {
    @Setter //column 단위로 setter 설정 가능은 함 !
    @Id //pk 설정
//    IDENTITY = auto increment 설정
//    auto = JPA 구현체가 자동으로 적절한 키 생성 전략 선택
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    String은 DB의 varchar로 변환
    private String name;
    @Column(nullable = false, length = 50) //name 옵션을 통해 DB의 칼럼명 별도 지정 가능
    private String email;
    private String password;
    @Setter
    @Column(name = "create_time")
    private LocalDateTime created_time;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_time = LocalDateTime.now();
    }

//    public Member() {
//
//    }
}
