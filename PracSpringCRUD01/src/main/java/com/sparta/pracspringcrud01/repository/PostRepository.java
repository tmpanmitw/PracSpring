package com.sparta.pracspringcrud01.repository;

import com.sparta.pracspringcrud01.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속   < T값 , 키값형식>
public interface PostRepository extends JpaRepository<Post, Long> {
}
