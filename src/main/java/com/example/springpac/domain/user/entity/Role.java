package com.example.springpac.domain.user.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
   /* GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;*/

    USER,  // 사용자 권한
    ADMIN  // 관리자 권한
}