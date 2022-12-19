package com.example.springpac.web.dto.comment;

import com.example.springpac.domain.comment.entity.Comment;
import lombok.Getter;



@Getter
public class CommentResponseDto {

    private Long id;

    private String comment;
    private String author;


    public CommentResponseDto(Comment entity){
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.author = entity.getAuthor();

    }
}
