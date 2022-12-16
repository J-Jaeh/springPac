package com.example.springpac.domain.posts.repository;


import com.example.springpac.domain.posts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}