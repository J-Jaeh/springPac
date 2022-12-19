package com.example.springpac.web.controller;

import com.example.springpac.sevice.posts.CommnetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController  {

    private  final CommnetService commnetService;


}
