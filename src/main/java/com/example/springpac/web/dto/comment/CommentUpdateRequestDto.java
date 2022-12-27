package com.example.springpac.web.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private String content;
    private String author;

    private Long postId;

    @Builder
    public CommentUpdateRequestDto(String content ,String author, Long postId){
        this.postId=postId;
        this.author = author;
        this.content = content;
    }
}
