package com.sparta.pracspringcrud01.dto;

import lombok.*;

//@Data
@Getter
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
//속성 1개일때는 http status 400 에러 , 2개이상일때만 되는 이유???????//NoArgs추가하니까됨
//JSON parse error: Cannot construct instance
// password 값만 받기 위함
public class RequestCheckPWDto {
//    private String title;
//    private String author;
//    private String content;
    private String password;
}
