package com.sparta.pracspringcrud01.service;

import com.sparta.pracspringcrud01.dto.*;
import com.sparta.pracspringcrud01.entity.Post;
import com.sparta.pracspringcrud01.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 1. 게시글 작성
    public ResponseDto createPost(RequestDto requestDto){
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new ResponseDto(post);
    }

    // 2. 게시글 전체 조회
    // List로 받은 게시물 리스트를 하나하나 빼서 Dto로 만들고 다시 리스트에 추가
    public List<ResponseDto> getPostAll(){
        //[1, 2, 3, 4, 5] -> ["1", "2", "3", "4", "5"]  타입변환하는거???????
        List<Post> postList =  postRepository.findAll();
        List<ResponseDto> responseDtoList = new ArrayList<>();
        for(Post post : postList){
            ResponseDto responseDto = new ResponseDto(post);
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    // 3. 게시글 Id로 조회
    public ResponseDto getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow();
        return new ResponseDto(post);
    }

    // 4. 게시글 수정
    //id값을 갖고 repository에 save하면 해당 id값에 추가
    public ResponseDto updatePost(Long id, RequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow();
        post.update(requestDto);
    // Setter 이용하는 방법
//        post.setTitle(requestDto.getTitle());
//        post.setAuthor((requestDto.getAuthor()));
//        post.setContent(requestDto.getContent());
//        post.setPassword(requestDto.getPassword());
        postRepository.save(post);
        return new ResponseDto(post);
    }

    // 5. 게시글 삭제
    public ResponseDeleteDto deletePost(Long id){
        postRepository.deleteById(id);
        return new ResponseDeleteDto(true);
    }

//     6. 게시글 비밀번호 확인
//    findById는 null값(해당Id가 존재하지 않을 경우)이 존재할 가능성이 있으므로 Optional형태에만 쓸수있다.
//     findById를 사용할때 orElseThrow처리(null값처리)를 해주면 굳이 Optional을 안써도된다.
    public ResponseCheckPWDto checkPasswordPost(Long id, RequestCheckPWDto requestCheckPWDto){
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getPassword().equals(requestCheckPWDto.getPassword())){
            return new ResponseCheckPWDto("비밀번호 일치");
        }else{
            return new ResponseCheckPWDto("비밀번호 불일치");
        }
    }
}
