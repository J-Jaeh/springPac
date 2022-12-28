package com.example.springpac.web.controller;

import com.example.springpac.sevice.PostsService;
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

    //게시글 쓴거 저장한거
    //이건 [js부분과  post-save html]부분을 확인해야 이해가 쉬워~ 거기서 가져온거를
    //requestDto에 담아서 가져옴 추가로  로그인시 헤더에 저장된 토큰도 가져옴
    @PostMapping("api/v1/posts")
    public PostsResponseDto save(@RequestBody PostsSaveRequestDto requestDto, HttpServletRequest request){

        return  postsService.save(requestDto,request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public String update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto , HttpServletRequest request){
        postsService.update(id, requestDto,request);
        return "게시글 수정에 성공했습니다";
    }
   /* @GetMapping("/api/v1/posts/{id}")
    public CommentAndPostResponseDto findById (@PathVariable Long id){

        return postsService.findById(id);  //포스트id에 해당하는 댓글을 가져오면됨  포스트 서비스 건들면 되겠네 ?
    }*/


    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id , HttpServletRequest request, @RequestBody PostDeleteRequestDto requestDto){
        postsService.delete(id,request,requestDto);
        return id;
    }
}
