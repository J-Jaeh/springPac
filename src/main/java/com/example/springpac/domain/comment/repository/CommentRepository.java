package com.example.springpac.domain.comment.repository;


import com.example.springpac.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    ArrayList<Comment> findByPostIdOrderByCreatedDateDesc(Long postId);

}
