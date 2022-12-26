package com.example.springpac.web.controller;

import com.example.springpac.sevice.posts.PostsService;
import com.example.springpac.web.dto.CommentAndPostResponseDto;
import com.example.springpac.web.dto.post.PostDeleteRequestDto;
import com.example.springpac.web.dto.post.PostsResponseDto;
import com.example.springpac.web.dto.post.PostsSaveRequestDto;
import com.example.springpac.web.dto.post.PostsUpdateRequestDto;
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
    public CommentAndPostResponseDto findById (@PathVariable Long id){

        return postsService.findById(id);  //포스트id에 해당하는 댓글을 가져오면됨  포스트 서비스 건들면 되겠네 ?
    }


    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id , HttpServletRequest request, @RequestBody PostDeleteRequestDto requestDto){
        postsService.delete(id,request,requestDto);
        return id;
    }
}
