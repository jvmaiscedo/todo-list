package com.jvmacedo.todo.repository;

import com.jvmacedo.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
  UserDetails findByEmail(String email);
  @Modifying
  @Transactional
  @Query("update users u set u.password = :password where u.email = :email")
  void updatePassword(@Param(value = "password") String password, @Param(value = "email")String email);
}
