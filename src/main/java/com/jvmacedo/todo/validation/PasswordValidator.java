package com.jvmacedo.todo.validation;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

  private static final int TAM_MINIMO_SENHA = 8;

  public boolean isSafe(String password) {
    return isNotNull(password) && hasMinimumLength(password);
  }

  private boolean isNotNull(String password) {
    return password != null;
  }

  private boolean hasMinimumLength(String password) {
    return password.length() >= TAM_MINIMO_SENHA;
  }
}