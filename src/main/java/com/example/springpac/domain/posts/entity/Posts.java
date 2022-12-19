package com.example.springpac.domain.posts.entity;

import com.example.springpac.web.dto.post.PostsSaveRequestDto;
import com.example.springpac.web.dto.post.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Column(nullable = false)
    private Long userId;

     //빌더를 생성자 위에 적용할경우... 생성자에 포함된 필드만 빌더에 포함..
    public  Posts(PostsSaveRequestDto requestDto, Long userId, String username){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = username;
        this.userId = userId;
    }

    public void update(PostsUpdateRequestDto requestDto){
        this.title =requestDto.getTitle();
        this.content=requestDto.getContent();
    }
}
