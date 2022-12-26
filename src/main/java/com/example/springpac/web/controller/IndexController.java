package com.example.springpac.web.controller;

import com.example.springpac.jwt.JwtUtil;
import com.example.springpac.sevice.posts.CommentService;
import com.example.springpac.sevice.posts.PostsService;
import com.example.springpac.web.dto.CommentAndPostResponseDto;
import com.example.springpac.web.dto.comment.CommentsListResponseDto;
import com.example.springpac.web.dto.post.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private  final CommentService commentService;
    private final JwtUtil jwtUtil;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        //model.addAttribute("userName", jwtUtil.getUserInfoFromToken(token));

        //유저네임을 가져오는 방식이하나 추가되면 정말 쉬울텐데...

        return "index";

    }
   /* @GetMapping("/api/v1/comment/{postId}")
    public String showComment(Model model,@PathVariable Long postId){

        model.addAttribute("comments",commentService.findAllByPostIdOrderByCreatedDateDesc(postId));

        return "post-see";
    }*/
   /* @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    } */

    @GetMapping("/posts/save")
    public  String postsSave(){
        return "post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
       CommentAndPostResponseDto dto = postsService.findById(id);
        /*PostsResponseDto dto = postsService.findById(id);*/
        model.addAttribute("post",dto);
        return "posts-update";
    }
    @GetMapping("/posts/see/{id}")
    public  String postsSee(@PathVariable Long id,Model model){
        CommentAndPostResponseDto dto =postsService.findById(id);

        model.addAttribute("see",dto);
        return "post-see";
    }
    /*@GetMapping("/posts/see/{id}")
    public String postsSee(@PathVariable Long id,Model model){
        CommentAndPostResponseDto dto =postsService.findById(id);
        *//*PostsResponseDto dto =postsService.findById(id);*//*
        model.addAttribute("see",dto);

        return "post-see";

    }*/


}
