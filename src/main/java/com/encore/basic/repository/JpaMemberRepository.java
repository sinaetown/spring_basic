package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {
    //    EntityManager는 jpa의 핵심 클래스 (객체)
//    Entity의 생명주기를 관리 -> DB와의 모든 상호작용을 책임
//    Entity를 대상으로 CRUD하는 기능을 제공
    private final EntityManager entityManager;

    //    여기서 @Autowired 생략 가능 (생성자가 한 개면 생략할 수 있음)
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
//        JPQL (Java Persistence Query Language) : jpa의 객체 지향 쿼리 문법
//        장점 : DB에 따라 문법이 달라지지 않는 객체 지향 언어, 컴파일 타임에서 잘못된 쿼리 check해줌 (SpringDataJpa의 @Query 기능)
//        단점 : DB 고유의 기능과 성능을 극대화하기 어려움
        List<Member> members = entityManager.createQuery("select m from Member m",
                Member.class).getResultList();
        return members;
    }

    @Override
    public Member save(Member member) {
//        persist : 전달된 entity(여기서는 Member)가
//        EntityManager의 관리 상태가 되도록 만들어주고
//        트랜잭션이 커밋될 때 DB에 저장 (insert, update 포함)
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
//        find메서드는 pk를 내개변수로 준다
        Member member = entityManager.find(Member.class, id);
//        name으로 조회할 때는
        return Optional.ofNullable(member);
    }

//    PK 외의 컬럼으로 조회할 때
//    public List<Member> findByName(String name) {
//        List<Member> members = entityManager.createQuery("select m from Member m " +
//                "where m.name= :name", Member.class).setParameter("name", name).getResultList();
//        return members;
//    }
}