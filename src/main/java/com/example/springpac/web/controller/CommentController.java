package com.example.springpac.web.controller;


import com.example.springpac.sevice.posts.CommentService;

import com.example.springpac.web.dto.comment.CommentResponseDto;
import com.example.springpac.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController  {

    private  final CommentService commentService;

    @PostMapping("/api/v1/comment/save")
    public CommentResponseDto save(@RequestBody CommentSaveRequestDto requestDto, HttpServletRequest request){

        return commentService.save(requestDto,request);
    }
      //인덱스 컨트롤러로
    /*@GetMapping("/api/v1/comment/{id}")
    public String showComment(Model model,@PathVariable Long id){
        model.addAttribute("comment",commentService.findByIdAndComment(id));

        return "post-see";
    }*/

}
