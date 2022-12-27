package com.example.springpac.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteRequestDto {
    private String username;
    private Long commentId;

    public CommentDeleteRequestDto(String username,Long commentId){
        this.username = username;
        this.commentId = commentId;
    }
}
