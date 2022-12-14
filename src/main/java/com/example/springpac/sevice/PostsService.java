package com.example.springpac.sevice;

import com.example.springpac.domain.comment.entity.Comment;
import com.example.springpac.domain.comment.repository.CommentRepository;
import com.example.springpac.domain.posts.entity.Posts;
import com.example.springpac.domain.posts.repository.PostsRepository;
import com.example.springpac.domain.user.entity.User;
import com.example.springpac.domain.user.repository.UserRepository;
import com.example.springpac.jwt.JwtUtil;
import com.example.springpac.web.dto.CommentAndPostResponseDto;
import com.example.springpac.web.dto.post.*;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    //토큰검사하고 맞으면 ~ 토큰에서 이름꺼내서 유저레포에서 동일 이름이 있나 조회 user객체 찾음
    //그러면이제 새로운 포스트를 포스트레포에 저장함 requestDto에서 받아온내용 + 유저아이디+유저이름
    @Transactional
    public PostsResponseDto save(PostsSaveRequestDto requestDto , HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token !=null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));

            Posts posts = postsRepository.saveAndFlush(new Posts(requestDto,user.getId(),user.getUsername()));
            //여기서 유저 고유id값을 저장안한다면 여기서 유저레포는 !

            return new PostsResponseDto(posts);

        }else
            throw new RuntimeException("저장 실패란다!");
    }


    @Transactional
    public  Long update(Long id, PostsUpdateRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;   //KEY VALUE 유저 : 유저이름 ?
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            /*User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));*/
            Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

           if ((claims.getSubject().equals(requestDto.getAuthor()))){   //첫번재가 레포에서가져욘 이름...  비교대상이 클래임
               posts.update(requestDto);

          }else {
               throw new RuntimeException("이름이달라서 실패란다!");
           }
           return posts.getId();
        } else
             throw new RuntimeException("로그인을 안해서 실패란다!");
    }

            public CommentAndPostResponseDto findById (Long id){ //여기서 추가해야할게 뭐냐 댓글을 가져오는거 추가시켜주면 되자나
                Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

                ArrayList<Comment> commentArrayList = commentRepository.findByPostsIdOrderByCreatedDateDesc(id);
                return new CommentAndPostResponseDto(entity,commentArrayList);
            }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllByOrderByCreatedDateDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id, HttpServletRequest request , PostDeleteRequestDto requestDto) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            if (requestDto.getUsername().equals(claims.getSubject())){
                postsRepository.delete(postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)));
                commentRepository.deleteByPostsId(id);
            }else
                throw new RuntimeException("글 작성자가 아니라서 실패란다!");
        }else
            throw new RuntimeException("삭제 실패란다!");
    }


}
