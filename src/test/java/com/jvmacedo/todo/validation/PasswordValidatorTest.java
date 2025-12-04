package com.jvmacedo.todo.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PasswordValidatorTest {

  private static PasswordValidator validator;

  @BeforeAll
  static void beforeAll() {
     validator = new PasswordValidator();
  }

  @Test
  void senhaDeveSerInvalidaSeForCurta() {
    boolean resultado = validator.isSafe("SenhaF2");
    assertFalse(resultado);
  }

  @Test
  void senhaDeveSerValidaSeCumprirRequisito() {
    boolean resultado = validator.isSafe("SenhaForte!");
    assertTrue(resultado);
  }

  @Test
  void senhaDeveFalharSeForNula() {
    assertFalse(validator.isSafe(null));
  }
}