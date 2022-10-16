package com.sparta.pracspringcrud01.dto;


import lombok.Getter;

@Getter
//@AllArgsConstructor //모든 필드(멤버변수)를 포함한 생성자 생성
public class RequestDto {
    private String title;
    private String author;
    private String content;
    private String password;

    //모든 필드를 포함한 생성자는 AllArgsConstructor로 대체가능
    public RequestDto(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }
}
