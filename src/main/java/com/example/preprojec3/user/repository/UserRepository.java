package com.example.preprojec3.user.repository;


import com.example.preprojec3.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
