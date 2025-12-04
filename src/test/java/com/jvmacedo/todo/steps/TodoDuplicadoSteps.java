package com.jvmacedo.todo.steps;

import com.jvmacedo.todo.dto.TodoDTO;
import com.jvmacedo.todo.model.Todo;
import com.jvmacedo.todo.repository.TodoRepository;
import com.jvmacedo.todo.service.TodoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class TodoDuplicadoSteps{

  @Autowired
  private TodoService todoService;

  @Autowired
  private TodoRepository todoRepository;

  private String userId = "user_teste_bdd";
  private Todo resultadoDaCriacao;

  @Dado("que o usuário já possui uma tarefa com título {string}")
  public void usuario_possui_tarefa(String titulo) {
    todoRepository.deleteAll();
    TodoDTO dto = new TodoDTO(userId, titulo, "Descricao", 1, false);
    todoService.create(dto);
  }

  @Quando("ele tenta criar outra tarefa com o título {string}")
  public void tenta_criar_outra_tarefa(String titulo) {
    TodoDTO dto = new TodoDTO(userId, titulo, "Descricao 2", 1, false);
    try {
      this.resultadoDaCriacao = todoService.create(dto);
    } catch (Exception e) {
      this.resultadoDaCriacao = null;
    }
  }

  @Entao("o sistema não deve criar a nova tarefa")
  public void sistema_nao_cria_nova_tarefa() {
    Assertions.assertNull(resultadoDaCriacao, "O sistema criou uma tarefa duplicada, mas não deveria!");
  }

  @E("deve retornar uma mensagem de erro ou nulo")
  public void deve_retornar_erro() {
    Assertions.assertNull(resultadoDaCriacao);
  }


}