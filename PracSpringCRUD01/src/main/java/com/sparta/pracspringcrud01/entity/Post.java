package com.sparta.pracspringcrud01.entity;

import com.sparta.pracspringcrud01.dto.RequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity  // Entity임을 명시
@NoArgsConstructor //  기본생성자 생성 (다른 생성자가 있을 경우 기본생성자가 자동으로 생성되지 않기 때문에)
// 왜 파라미터를 안받는 기본생성자가 Dto에는 필요없고 Entity에는 반드시 필요한가?
// Get을 하기위해 Proxy가 필요해서 그런가??
public class Post {

    @Id   // 기본키(Primary key)값 , Entity는 반드시 키값 필요
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 키값자동생성, IDENTY는 4가지 방식중 하나
    private Long id;

    private String title;

    private String author;

    private String content;

    private String password;

    public Post(String title, String author, String content, String password){
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public Post(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    //기본생성자  @NoArgsConstructor(다른생성자가 있을경우 기본생성자 자동생성) 로 대체가능
//    public Post(){
//    }
    // @Getter 함수로 대체가능
//    public Long getId(){
//        return id;
//    }
//
//    public String getTitle(){
//        return title;
//    }
//
//    public String getAuthor(){
//        return author;
//    }
//
//    public String getContent(){
//        return content;
//    }
//
//    public String getPassword(){
//        return password;
//    }
}


