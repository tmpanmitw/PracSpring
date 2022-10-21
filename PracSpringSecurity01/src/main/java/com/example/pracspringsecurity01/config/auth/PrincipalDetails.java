package com.example.pracspringsecurity01.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어 줍니다.
// 일반적인 session 속에 시큐리티 자신만의 session을 생성
// 그 시큐리티 자신만의 session의 키값 = Security ContextHolder
// 이 Security Session에 들어갈수 있는 정보/객체(오브젝트)는 오직 Authentication 타입 객체
//  따라서, Authentication 타입에 User정보가 있어야됨
// Authentication 안의 유저 정보는 UserDetails에 객체에 존재
// User 객체 타입 = UserDetails 타입 객체
// Security Session 속에 Authentication 속에 UserDetails 객체타입의 유저정보 존재
// Security Session => Authentication => UserDetails(PrincipalDetails)

import com.example.pracspringsecurity01.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// UserDetails는 따로 @Component 없이 PrincipalDetailService에서 new를 통해 생성
public class PrincipalDetails implements UserDetails {
    // UserDetilas 는 인터페이스 형식이므로 implements 후 override를 채워야 된다.
    // 인터페이스는 일종의 양식에 불과

    // member객체를 PrincipalDetails(UserDetails)객체로 변환하기 위해
    // 콤포지션
    private Member member;
    //생성자 생성
    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // 해당 User의 권한을 리턴하는 곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ArrayList 는 Collection 의 자식
        Collection<GrantedAuthority> collect = new ArrayList<>();
        // GrantedAuthority 또한 interface이므로 override해줘야됨 (양식채워줘야됨)
        // 이부분 뭔말인지 잘모르겟다~~~~~~~~??
        // 객체를 생성했는데 왜 함수가 호출안해도 바로 실행되는가?? interface는 원래 그런가...??
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    //니 계정 안만료되었니?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //니 계정 안잠겻니?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 니 비밀번호 너무 오래사용한건 아니니?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 니 계정이 활성화 되었니?
    @Override
    public boolean isEnabled() {
        // 만약에 우리 사이트에서 1년동안 회원이 로그인을 안하면 휴먼계정으로 하기로함
        // 현재시간 - 로긴시간(loginDate) => 1년을 초과하면 return false;
        return true;
    }
}
