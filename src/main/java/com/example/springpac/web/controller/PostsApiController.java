package com.example.springpac.web.controller;

import com.example.springpac.sevice.posts.PostsService;
import com.example.springpac.web.dto.PostsResponseDto;
import com.example.springpac.web.dto.PostsSaveRequestDto;
import com.example.springpac.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("api/v1/posts")
    public PostsResponseDto save(@RequestBody PostsSaveRequestDto requestDto, HttpServletRequest request){

        return  postsService.save(requestDto,request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto ,HttpServletRequest request){
        return postsService.update(id, requestDto,request);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }


    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id ){
        postsService.delete(id);
        return id;
    }
}
