package com.example.springpac.web.controller;


import com.example.springpac.sevice.posts.CommentService;

import com.example.springpac.web.dto.comment.CommentDeleteRequestDto;
import com.example.springpac.web.dto.comment.CommentResponseDto;
import com.example.springpac.web.dto.comment.CommentSaveRequestDto;
import com.example.springpac.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController  {

    private  final CommentService commentService;

    @PostMapping("/api/v1/posts/{id}/comment")
    public CommentResponseDto save(@RequestBody CommentSaveRequestDto requestDto, HttpServletRequest request,@PathVariable Long id){

        return commentService.save(requestDto,request,id);
    }
      //인덱스 컨트롤러로 아...포스트 조회에 통합하면됨
    /*@GetMapping("/api/v1/comment/{id}")
    public String showComment(Model model,@PathVariable Long id){
        model.addAttribute("comment",commentService.findByIdAndComment(id));

        return "post-see";
    }*/
    //삭제버튼에 댓글 id를 심어놓아서 json으로 전송..? 그럼 여기서 어떻게 받지 리퀘스트 바디로 받지
    //댓글작성자의 이름은 requestDto에 담기는거 맞지 ?!

    @DeleteMapping("/api/v1/posts/{id}/comment")
    public String deleteComment( HttpServletRequest request, @RequestBody CommentDeleteRequestDto requestDto){

        commentService.delete(request,requestDto);
    return "댓글삭제에 성공했습니다";
    }

    @PutMapping("/api/v1/posts/{id}/comment")
    public String update(HttpServletRequest request, @RequestBody CommentUpdateRequestDto requestDto){


     return commentService.update(requestDto,request);
    }

}
