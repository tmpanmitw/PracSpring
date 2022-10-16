package com.sparta.pracspringcrud01.controller;

import com.sparta.pracspringcrud01.dto.*;
import com.sparta.pracspringcrud01.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api") //URL값의 공통된부분을 추출
@RequiredArgsConstructor //final이 붙은 필드의 생성자를 자동생성해줌
@RestController  //json형식으로 객체데이터를 반환, 그냥 Controller는 view를 반환(MVC)
public class PostController {

    //@RequiredArgsConstructor로 생성자를 자동생성해줌
    private final PostService postService;

    // 1. 게시글 작성
    @PostMapping("/posts")
    public ResponseDto creatPost(@RequestBody RequestDto requestDto){
        return postService.createPost(requestDto);
    }

    // 2. 게시글 전체 조회
    @GetMapping("/posts")
    public List<ResponseDto> getPostAll(){
        return postService.getPostAll();
    }

    // 3. 게시글 Id로 조회
    @GetMapping("/posts/{id}")
    //@PathVariable("id") Long id VS @RequestParam(value = "id") Long id 차이점?? String만 받는건가
    public ResponseDto getPostById(@PathVariable("id") Long id){
        return postService.getPostById(id);
    }

    // 4. 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseDto updatePost(@PathVariable("id") Long id, @RequestBody RequestDto requestDto){
        return postService.updatePost(id, requestDto);
    }

    // 5. 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseDeleteDto deletePost(@PathVariable("id") Long id){
        return postService.deletePost(id);
    }

    // 6. 게시글 비밀번호 확인
    @PostMapping("/posts/{id}")
    public ResponseCheckPWDto checkPasswordPost(@PathVariable("id") Long id, @RequestBody RequestCheckPWDto requestCheckPWDto){
        return postService.checkPasswordPost(id, requestCheckPWDto);
    }
}


