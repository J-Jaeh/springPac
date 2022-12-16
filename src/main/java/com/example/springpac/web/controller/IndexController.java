package com.example.springpac.web.controller;

import com.example.springpac.sevice.posts.PostsService;
import com.example.springpac.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public  String postsSave(){
        return "post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
    @GetMapping("/posts/see/{id}")
    public String postsSee(@PathVariable Long id,Model model){
        PostsResponseDto dto =postsService.findById(id);
        model.addAttribute("see",dto);

        return "post-see";

    }
}
