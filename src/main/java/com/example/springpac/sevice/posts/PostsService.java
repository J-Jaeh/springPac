package com.example.springpac.sevice.posts;

import com.example.springpac.domain.posts.entity.Posts;
import com.example.springpac.domain.posts.repository.PostsRepository;
import com.example.springpac.domain.user.entity.User;
import com.example.springpac.domain.user.repository.UserRepository;
import com.example.springpac.jwt.JwtUtil;
import com.example.springpac.web.dto.PostsListResponseDto;
import com.example.springpac.web.dto.PostsResponseDto;
import com.example.springpac.web.dto.PostsSaveRequestDto;
import com.example.springpac.web.dto.PostsUpdateRequestDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

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

            return new PostsResponseDto(posts);

        }else
        return null;
    }

    @Transactional
    public  Long update(Long id, PostsUpdateRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));
            Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));


           if ((user.getUsername().equals(requestDto.getAuthor()))){   //첫번재가 레포에서가져욘 이름...  비교대상이 클래임
               posts.update(requestDto);

          }
            return posts.getId();
        } else
            return id;
    }

            public PostsResponseDto findById (Long id){
                Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

                return new PostsResponseDto(entity);
            }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllByOrderByCreatedDateDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }


}
