package com.example.springpac.web.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String comment;
    private String author;



    @Builder
    CommentSaveRequestDto(String comment, String author){

        this.comment = comment;
        this.author = author;
    }
}
