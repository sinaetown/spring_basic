package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//Entity 어노테이션을 통해 mariaDB의 테이블 및 컬럼을 자동 생성
//Class명은 테이블명, 변수명은 컬럼명
@Getter //보통 Getter만 사용
@NoArgsConstructor
//@AllArgsConstructor //모든 매개변수를 넣은 생성자 (기본 생성자 없어짐)
//@NoArgsConstructor
// -> 기본 생성자 만들어줌, reflection 사용하기 위해 (member.name, member.email..으로 사용하기 위해)
// -> 직접 접근 위해
public class Member {
    @Setter //column 단위로 setter 설정 가능은 함!
    @Id //pk 설정
//    IDENTITY = auto increment 설정
//    auto = JPA 구현체가 자동으로 적절한 키 생성 전략 선택
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    String은 DB의 varchar (255)로 변환
    @Setter
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Setter
    private String password;
    @Setter
    @Column(name = "create_time")  //name 옵션을 통해 DB의 칼럼명 별도 지정 가능
    @CreationTimestamp
    private LocalDateTime created_time; // Java에서 createTime이라 설정해도 DB에서는 created_time으로 설정됨
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void update(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
