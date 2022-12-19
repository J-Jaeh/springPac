package com.example.springpac.domain.comment.entity;


import com.example.springpac.domain.posts.entity.BaseTimeEntity;
import com.example.springpac.web.dto.comment.CommentSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment  extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String comment;

    @Column
    private String author;   //흠 포스트 아이디를 담았으면 하는데

    @Column
    private Long postId;  //전달받을 수 있지 ? ㅇㅋ

    @Column
    private Long userId; //댓글 작성자를 알기 위한

    public Comment(CommentSaveRequestDto requestDto,Long userId,String username){
        this.comment = requestDto.getComment();
        this.postId = requestDto.getPostId();
        this.author = username;
        this.userId = userId;
    }  //요청 dto에 들어있는것은 ? 글번호  내용 //토큰에는 유저네임들어있음
}
