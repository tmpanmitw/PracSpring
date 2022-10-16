package com.sparta.pracspringcrud01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
//비밀번호 체크 결과값 전달
public class ResponseCheckPWDto {
    private String result;
}
