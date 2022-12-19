package com.example.springpac.web.controller;


import com.example.springpac.sevice.posts.CommentService;

import com.example.springpac.web.dto.comment.CommentResponseDto;
import com.example.springpac.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController  {

    private  final CommentService commentService;

    @PostMapping("api/v1/comment/save")
    public CommentResponseDto save(@RequestBody CommentSaveRequestDto requestDto, HttpServletRequest request){

        return commentService.save(requestDto,request);
    }

}
