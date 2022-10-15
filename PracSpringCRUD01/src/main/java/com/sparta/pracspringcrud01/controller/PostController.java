package com.sparta.pracspringcrud01.controller;

import com.sparta.pracspringcrud01.dto.RequestDto;
import com.sparta.pracspringcrud01.entity.Post;
import com.sparta.pracspringcrud01.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api") //URL값의 공통된부분을 추출
@RequiredArgsConstructor //final이 붙은 필드의 생성자를 자동생성해줌
@RestController  //json형식으로 객체데이터를 반환, 그냥 Controller는 view를 반환(MVC)
public class PostController {

    //@RequiredArgsConstructor로 생성자를 자동생성해줌
    private final PostRepository postRepository;
    // 1. 게시글 작성
    @PostMapping("/posts")
    public Post creatPost(@RequestBody Post post){
        postRepository.save(post);
        return post;
    }

    // 2. 게시글 전체 조회
    @GetMapping("/posts")
    public List<Post> getPostAll(){
        postRepository.findAll();
        return postRepository.findAll();
    }

    // 3. 게시글 Id로 조회
    @GetMapping("/posts/{id}")
    //findById는 null값(해당Id가 존재하지 않을 경우)이 존재할 가능성이 있으므로 Optional형태에만 쓸수있다.
    //@PathVariable("id") Long id VS @RequestParam(value = "id") Long id 차이점?? String만 받는건가
    public Optional<Post> getPostById(@PathVariable("id") Long id){
        postRepository.findById(id);
        return postRepository.findById(id);
    }

    // 4. 게시글 수정
    @PutMapping("/posts/{id}")
    //id값을 갖고 repository에 save하면 해당 id값에 추가
    public Post updatePost(@PathVariable("id") Long id, @RequestBody RequestDto requestDto){
        Post post = new Post(requestDto);
        post.setId(id);
        postRepository.save(post);
        return post;
    }

    // 5. 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable("id") Long id){
        postRepository.deleteById(id);
        return id;
    }

    // 6. 게시글 비밀번호 확인
    @PostMapping("/posts/{id}")
    public String checkPasswordPost(@PathVariable("id") Long id, @RequestBody RequestDto requestDto){
        // findById를 사용할때 orElseThrow처리(null값처리)를 해주면 굳이 Optional을 안써도된다.
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getPassword().equals(requestDto.getPassword())){
            return "비밀번호 일치";
        }else{
            return "비밀번호 불일치";
        }
    }
}


