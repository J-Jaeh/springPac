package com.example.springpac.web.dto.comment;

import com.example.springpac.domain.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsListResponseDto {
    private Long id;
    private String comment;
    private String author;
    private LocalDateTime modifiedDate;

    public CommentsListResponseDto(Comment entity){
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
