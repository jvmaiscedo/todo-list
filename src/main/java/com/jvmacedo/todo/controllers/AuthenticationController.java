package com.jvmacedo.todo.controllers;

import com.jvmacedo.todo.dto.AuthenticationDTO;
import com.jvmacedo.todo.dto.LoginResponseDTO;
import com.jvmacedo.todo.service.TokenService;
import com.jvmacedo.todo.model.User;
import com.jvmacedo.todo.repository.UserRepository;
import com.jvmacedo.todo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;
  @Autowired
  private TokenService tokenService;
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO user){
    var usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((User)auth.getPrincipal());//gerando o token para acessar os demais endpoints
    var userId = ((User) auth.getPrincipal()).getId();//guardando o Id de usuario para manipular as tasks criadas por ele
    return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token, userId));//Retornando o token e id de usuario

  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid AuthenticationDTO user){
    if(this.userService.create(user)==null){
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login/forgotPassword")
  public ResponseEntity resetPassword(@RequestBody AuthenticationDTO user){
    if(userService.updatePassword(user.password(),user.email())){
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

}
