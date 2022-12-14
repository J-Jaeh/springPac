package com.example.springpac.web.dto.post;

import com.example.springpac.domain.comment.entity.Comment;
import com.example.springpac.domain.posts.entity.Posts;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    /*private ArrayList<Comment> comment;*/

    public PostsResponseDto(Posts entity/*ArrayList<Comment> commentList*/){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
       /* this.comment = commentList;
*/
    }
}
