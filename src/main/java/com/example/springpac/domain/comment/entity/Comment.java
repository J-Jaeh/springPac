package com.example.springpac.domain.comment.entity;


import com.example.springpac.domain.posts.entity.BaseTimeEntity;
import com.example.springpac.domain.posts.entity.Posts;
import com.example.springpac.web.dto.comment.CommentSaveRequestDto;
import com.example.springpac.web.dto.comment.CommentUpdateRequestDto;
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

    /* @Column
    private Long userId; //없어도 될듯한데 ...*/
    @Column
    private Long postsId;  //전달받을 수 있지 ? ㅇㅋ



   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID",nullable = false) //댓글이 게시글을 필요로해...근데..댓글은..게시글의 기능을..필요로하지않아..
    private Posts posts;*/

    ///  감사합니다
    public Comment(CommentSaveRequestDto requestDto,String username,Long postId){
        this.comment = requestDto.getComment();
        this.postsId = postId;
        this.author = username;

    }  //요청 dto에 들어있는것은 ? 글번호  내용 //토큰에는 유저네임들어있음
    public void update(CommentUpdateRequestDto requestDto){
        this.comment= requestDto.getContent();
    }
}
