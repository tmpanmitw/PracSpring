package com.example.pracspringsecurity01.config.auth;

import com.example.pracspringsecurity01.entity.Member;
import com.example.pracspringsecurity01.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// 시큐리티 설정에서 .loginProcessingUrl("/login")
// "/login" 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줌
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
// 이건 그냥 시큐리티의 규칙
// username은 loginForm에서 받은 username이다. 반드시 일치해줘야함
// 실행된 loadUserByUsername 함수의 UserDetails 타입의 리턴값이 Authentication 내부로 들어간다.
// Authentication 자동으로 만들어짐
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    //시큐리티 session = Authentication = UserDetails
    //시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " +username);
        Member memberEntity = memberRepository.findByUsername(username);
        if(memberEntity !=null){
            // return된 값이 Authentication 내부에 들어간다.
            return new PrincipalDetails(memberEntity);
        }
        return null;
    }
}
