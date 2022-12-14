package com.example.springpac.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder   //빌더를 생성자 위에 적용할경우... 생성자에 포함된 필드만 빌더에 포함..
    public  Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
