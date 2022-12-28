package com.example.springpac.web.controller;

import com.example.springpac.jwt.JwtUtil;
import com.example.springpac.sevice.CommentService;
import com.example.springpac.sevice.PostsService;
import com.example.springpac.web.dto.CommentAndPostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//인덱스컨트롤러는 로직없이 순수 조회만 하는 기능 ! 즉 GET
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    /*private  final CommentService commentService;
    private final JwtUtil jwtUtil;*/


    //MVC방식으로  기본 주소로 들어가면 model 에 게시글을 조회 한걸 집어넣어줌..
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        //model.addAttribute("userName", jwtUtil.getUserInfoFromToken(token));

        //유저네임을 가져오는 방식이하나 추가되면 정말 쉬울텐데...

        return "index";

    }


    //이건 단순히 게시글 작성화면을 보여주는것
    @GetMapping("/posts/save")
    public  String postsSave(){

        return "post-save";
    }

    //@PathVariable로 url에 있는 id가져와서 이걸 서비스에서 findById로 찾아서 dto로넘겨줌..!
    //CommentAndPost인 이유는.. 게시글조회할때 댓글도 그 게시글 아이디에 해당하는 댓글도 조회할려고..!
    //post-see가보면 읽기전용으로 해놈! 이놈!
    @GetMapping("/posts/see/{id}")
    public  String postsSee(@PathVariable Long id,Model model){
        CommentAndPostResponseDto dto =postsService.findById(id);

        model.addAttribute("see",dto);
        return "post-see";
    }

    //이거는 게시글 수정! 방식은 위와 유사함
    //이거 왜 commentAndPostResposeDto 썼냐면... postsService의 findByid 재활용하기 위해
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
       CommentAndPostResponseDto dto = postsService.findById(id);
        /*PostsResponseDto dto = postsService.findById(id);*/
        model.addAttribute("post",dto);
        return "posts-update";
    }


    /*@GetMapping("/posts/see/{id}")
    public String postsSee(@PathVariable Long id,Model model){
        CommentAndPostResponseDto dto =postsService.findById(id);
        *//*PostsResponseDto dto =postsService.findById(id);*//*
        model.addAttribute("see",dto);

        return "post-see";

    }*/


}
