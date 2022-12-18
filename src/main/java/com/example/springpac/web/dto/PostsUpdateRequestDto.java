package com.example.springpac.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    private String author;

    @Builder
    public PostsUpdateRequestDto(String title, String author ,String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
