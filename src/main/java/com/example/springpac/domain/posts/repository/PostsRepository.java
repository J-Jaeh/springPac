package com.example.springpac.domain.posts.repository;

import com.example.springpac.domain.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query(value = "SELECT p FROM Posts  p ORDER BY p.Id DESC")
    List<Posts> findAllDesc();
}
