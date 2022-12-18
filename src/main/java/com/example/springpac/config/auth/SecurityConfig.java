/*
package com.example.springpac.config.auth;

import com.example.springpac.domain.user.entity.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2userService customOAuth2userService;

    @Bean
    public DefaultSecurityFilterChain  filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests(authorize->authorize
                        .antMatchers("/","/css/**","/h2-console/**","/profile").permitAll()
                        .antMatchers("/api/vi/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated())
                .logout(logout->logout
                        .logoutSuccessUrl("/"))
                .oauth2Login(oauth2Login->oauth2Login
                        .userInfoEndpoint()
                        .userService(customOAuth2userService));

        return http.build();


    }
}

 */
