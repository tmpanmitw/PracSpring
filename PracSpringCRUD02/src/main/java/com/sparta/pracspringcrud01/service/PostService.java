package com.sparta.pracspringcrud01.service;

import com.sparta.pracspringcrud01.dto.RequestDto;
import com.sparta.pracspringcrud01.entity.Post;
import com.sparta.pracspringcrud01.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 1. 게시글 작성
    public void createPost(Post post){
        postRepository.save(post);
    }

    // 2. 게시글 전체 조회
    public List<Post> getPostAll(){
        return postRepository.findAll();
    }

    // 3. 게시글 Id로 조회
    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow();
    }

    // 4. 게시글 수정
    //id값을 갖고 repository에 save하면 해당 id값에 추가
    public void updatePost(Long id, Post post){
        post.setId(id);
        postRepository.save(post);
    }

    // 5. 게시글 삭제
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    // 6. 게시글 비밀번호 확인
    //findById는 null값(해당Id가 존재하지 않을 경우)이 존재할 가능성이 있으므로 Optional형태에만 쓸수있다.
    // findById를 사용할때 orElseThrow처리(null값처리)를 해주면 굳이 Optional을 안써도된다.
    public String checkPasswordPost(Long id, RequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getPassword().equals(requestDto.getPassword())){
            return "비밀번호 일치";
        }else{
            return "비밀번호 불일치";
        }
    }
}
