package com.example;

import com.example.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findById(String id);
}