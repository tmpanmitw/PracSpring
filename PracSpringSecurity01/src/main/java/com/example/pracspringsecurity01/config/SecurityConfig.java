package com.example.pracspringsecurity01.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity  // 활성화 : 스프링 시큐리티 필터가 스프링 필터체인에 등록
// 이전 방식 : WebSecurityConfigurerAdapter 상속 후 configure 메소드 오버라이딩하여 설정
//public class SecurityConfig extends WebSecurityConfigurerAdapter

//@secured (특정메소드에 간단하게 접근권한 설정) 어노테이션 활성화
//@preAuthorize() 어노테이션 활성화
//@PostAuthorize() 어노테이션 활성화
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
// 권장 방식 : SecurityFilterChain 을 빈으로 등록
public class SecurityConfig{
    // 해당 메서의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // csrf(사이즈간 위조요청) protection(default값)을 비활성화
        http.csrf().disable();
        // 접근권한설정 (Security는 기본적으로 모든 페이지 인증 필요가 default)
        http
                // h2-console 창 깨져서 출력될때 추가
                .headers()
                    .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                // .authenticated 해당주소접근시 인증(Login)필요
                    .antMatchers("/user/**").authenticated()
                // .access : 해당주소접근시 인증(Login) 및 추가권한 필요
                    .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                // 그 밖에 설정을 안한 나머지 다른 요청은 모두 허용
                    .anyRequest().permitAll()
                //권한이 없는 페이지 들어갈때 login 페이지로 자동으로 이동하게함.
                .and()
                //일반적인 로그인 방식 (로그인 폼페이지, 로그인 처리 성공 실패 등)을 사용하겠다는 의미
                .formLogin()
                // 사용자가 따로 만든 로그인 페이지를 사용
                    .loginPage("/loginForm")
                // 로그인 즉 인증 처리를 하는 URL 설정 ,  “/login” 가 호출되면 인증처리를 수행하는 필터가 호출
                // "/login" 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줌
                // login.html같은 파일이 없어도됨 주소값만 있으면 됨.
                    .loginProcessingUrl("/login")
                // 정상적으로 인증성공했을 경우 이동하는 페이지를 설정 , 디폴트값 "/"
                // but, 권한이 없는 페이지 ex)use/**같은 페이지에서 로그인 폼페이지를 통해
                // 로그인할 경우 인증성공하면 해당페이지로 이동
                    .defaultSuccessUrl("/");
                // 정상적인증 성공 후 별도의 처리가 필요한경우 커스텀 핸들러를 생성하여 등록
//                .successHandler(new CustomAuthenticationSuccessHandler("/main"))
                // 인증이 실패 했을 경우 이동하는 페이지를 설정
//                .failureUrl("/login-fail")
                // 인증 실패 후 별도의 처리가 필요한경우 커스텀 핸들러를 생성하여 등록
//                .successHandler(new CustomAuthenticationFailureHandler("/login-fail"))

        // Bean 등록방식 시 리턴값 필요
        return http.build();
    }
}