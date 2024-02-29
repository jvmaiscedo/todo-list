package com.jvmacedo.todo.service;

import com.jvmacedo.todo.dto.AuthenticationDTO;
import com.jvmacedo.todo.model.User;
import com.jvmacedo.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
  @Autowired
  UserRepository userRepository;
  public User create (AuthenticationDTO userDTO){
    if(userRepository.findByEmail(userDTO.email())!=null){
      return null;
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
    User newUser = new User(userDTO.email(), encryptedPassword);
    return userRepository.save(newUser);
  }
  public boolean updatePassword(String password, String email){
    if(userRepository.findByEmail(email)==null){
      return false;
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(password);
    userRepository.updatePassword(encryptedPassword, email);
    return true;
  }
}
