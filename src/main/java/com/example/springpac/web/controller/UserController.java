package com.example.springpac.web.controller;



import com.example.springpac.jwt.JwtUtil;
import com.example.springpac.sevice.posts.PostsService;
import com.example.springpac.sevice.posts.UserService;
import com.example.springpac.web.dto.login.LoginRequestDto;
import com.example.springpac.web.dto.login.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final JwtUtil jwtUtil;
    private final PostsService postsService;

    private final UserService userService;
    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return  "redirect:/api/user/login";
    }
   /* @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return "redirect:/";
    }*/
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
        return "success";
    }

}