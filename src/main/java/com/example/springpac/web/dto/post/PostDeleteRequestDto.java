package com.example.springpac.web.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDeleteRequestDto {
    private String username;

    public PostDeleteRequestDto(String username) {
        this.username = username;

    }
}
