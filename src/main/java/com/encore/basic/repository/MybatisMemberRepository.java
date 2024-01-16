package com.encore.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
//mybatis 레파지토리로 쓰겠다 ! 라는 어노테이션
@Mapper
public interface MybatisMemberRepository extends MemberRepository {
//    본문에 MybatisRepository에서 사용할 메서드 명세를 정으해야 하지만
//    MemberRepository에서 상속받고 있으므로 생략 가능
//    실질적인 쿼리 등의 구현은 resources/mapper/MemberMapper.xml 파일에서 수행
}
