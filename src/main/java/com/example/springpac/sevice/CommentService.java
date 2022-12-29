package com.example.springpac.sevice;

import com.example.springpac.domain.comment.entity.Comment;
import com.example.springpac.domain.comment.repository.CommentRepository;
import com.example.springpac.domain.posts.entity.Posts;
import com.example.springpac.domain.posts.repository.PostsRepository;
import com.example.springpac.domain.user.entity.User;
import com.example.springpac.domain.user.repository.UserRepository;
import com.example.springpac.jwt.JwtUtil;
import com.example.springpac.web.dto.comment.*;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostsRepository postsRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional   //포스트 아이디를 받아와야함... 그래야...포스트 조회가능..?
    public CommentResponseDto save(CommentSaveRequestDto requestDto, HttpServletRequest request, Long postsId) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            /*User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));*/

            Comment comment = commentRepository.saveAndFlush(new Comment(requestDto, claims.getSubject(),postsId));



            return new CommentResponseDto(comment);

        } else
            return null;
    }

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findAllByPostIdOrderByCreatedDateDesc(Long postsId){
        return commentRepository.findByPostsIdOrderByCreatedDateDesc(postsId).stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
    }
    @Transactional
    public void delete(HttpServletRequest request , CommentDeleteRequestDto requestDto) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            if (requestDto.getUsername().equals(claims.getSubject())){
                commentRepository.delete(commentRepository.findById(requestDto.getCommentId()).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + requestDto.getCommentId())));
            }else
                throw new RuntimeException("글 작성자가 아니라서 실패란다!");
        }else
            throw new RuntimeException("삭제 실패란다!");
    }
    @Transactional
    public  String update(CommentUpdateRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            /*User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));*/
            Comment comment = commentRepository.findById(requestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당 댓글 없습니다. id =" + requestDto.getPostId()));

            if ((claims.getSubject().equals(requestDto.getAuthor()))){   //유저레포에 있는 이름이 Dto에 담긴 이름과 동일하다면 작성자확인완료

                comment.update(requestDto);

            }else {
                throw new RuntimeException("이름이달라서 실패란다!");
            }
            return " 댓글 업데이트 성공";
        } else
            throw new RuntimeException("로그인을 안해서 실패란다!");
    }
}
