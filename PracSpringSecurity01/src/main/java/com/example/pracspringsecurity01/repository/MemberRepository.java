package com.example.pracspringsecurity01.repository;

import com.example.pracspringsecurity01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


// 기본적인 CRUD 함수를 JpaRepository가 들고 있음.
//  JpaRepository를 상속했기 때문에 @Repository라는 어노테이션이 없어도 IoC에 추가됨(빈으로 자동으로 등록됨)

public interface MemberRepository extends JpaRepository<Member, Long> {
    // findByUsername을 정의해준다. JpaRepository의 findby~~ 함수양식을 이용해서 커스텀
    // findBy 규칙-> Username문법
    // select * from user where username = 1?  (쿼리, ? 부분에 파라미터가 들어감) 호출
    public Member findByUsername(String username);
    // JPA Query method 검색해봐!!
    // select * from user where email = ?
//    public Member findByEmail(String email);
}
