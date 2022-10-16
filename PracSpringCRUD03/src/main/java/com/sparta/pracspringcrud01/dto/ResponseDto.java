package com.sparta.pracspringcrud01.dto;

import com.sparta.pracspringcrud01.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String content;
    private String author;

    // password는 보여주기 않기 위해 response에 추가하지 않는다.
    public ResponseDto(Post post){
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();

    }

}
