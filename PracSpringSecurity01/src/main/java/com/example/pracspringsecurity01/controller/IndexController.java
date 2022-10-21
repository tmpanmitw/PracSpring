package com.example.pracspringsecurity01.controller;

import com.example.pracspringsecurity01.entity.Member;
import com.example.pracspringsecurity01.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // view를 리턴하겠다!!
public class IndexController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // localhost:8080  ,   localhost:8080/
    // thymeleaf 사용
    @GetMapping({"", "/"})
    public String index(){
        // thymeleaf 기본폴더 src/main/resources/
        // 뷰리졸버 설정 : templates (prefix), .html(suffix)    의존성 주입시 생략가능!
        return "index";   //src/main/resources/templates/index.html
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    // 스프링시큐리티 해당주소를 낚아채버리네요!! - SecurityConfig 파일 생성 후 작동안함.
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    // joinForm에서 Form 태그의 Post방식으로 넘겨준 데이터를 User user에 맵핑
    public String join(Member member){
        // 콘솔에서 확인
        System.out.println(member);
        member.setRole("ROLE_USER");
        String rawPassword = member.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encodePassword);
        System.out.println(member);
        //이렇게 하면 회원가입 잘됨 but, 시큐리티로 로그인을 할 수 없음
        // 이유는 패스워드가 암호화가 안되었기 때문에
        memberRepository.save(member);
        // redirect는 클라이언트의 요청에 의해 서버의 DB에 변화가 생기는 작업에 사용
        // 예를 들어 DB의 유저 테이블을 변경하는 회원가입과 같은 경우에는 리다이렉트가
        // 사용되어야 요청을 중복해서 보내는 것을 방지할 수 있다.
        return "redirect:/loginForm";
    }

    // @Secured 특정메소드에 간단하게 접근권한 설정하는 방법
    // @EnableGlobalMethodSecurity(securedEnabled = true) 필요
    // 권한 role 하나만 걸고 싶을때는 @Secured 로 보통 설정
    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    // @PreAuthorize 특정메소드에 간단하게 접근권한 설정하는 방법
    // @PreAuthorize data()함수가 작동하기 직전에 실행됨
    // @EnableGlobalMethodSecurity(securedEnabled = true) 필요
    // 권한 role 두개이상 걸고 싶을때는 @PreAuthorize
    // @PostAuthorize data()함수가 작동한 이후에 실행됨 (거의 안씀)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터정보";
    }



    // @ResponseBody : 자바객체 -> Http 응답 본문의 객체로 변환하여 클라이언트로 전송
//    @GetMapping("/joinProc")
//    public @ResponseBody String joinProc(){
//        return "회원가입 완료됨";
//    }
}
