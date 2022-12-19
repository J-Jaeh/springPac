package com.example.springpac.domain.posts.repository;

import com.example.springpac.domain.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    //@Query(value = "SELECT p FROM Posts  p ORDER BY p.Id DESC")

    List<Posts> findAllByOrderByCreatedDateDesc();


   // Optional<Posts> findByIdAndUserId(Long id,Long userId);

    Optional<Posts> findById(Long id);
}
