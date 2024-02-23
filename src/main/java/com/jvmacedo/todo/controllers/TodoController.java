package com.jvmacedo.todo.controllers;


import com.jvmacedo.todo.dto.TodoDTO;
import com.jvmacedo.todo.model.Todo;
import com.jvmacedo.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {
  @Autowired
  public TodoService todoService;
  @GetMapping
  private ResponseEntity<Object> getAll(){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll());
  }
  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable UUID id){
    Optional<Todo> todo = todoService.getById(id);
    if(!todo.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(todo);
  }
  @GetMapping("/priority/{priority}")
  public ResponseEntity<Object> getByPriority(@PathVariable Integer priority){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.getByPriority(priority));
  }
  @GetMapping("/status/{status}")
  public ResponseEntity<Object> getByStatus(@PathVariable boolean status){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.getByStatus(status));
  }
  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid TodoDTO todoDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todoDTO));
  }
  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody TodoDTO todo){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.update(todo, id));
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id){
    Optional<Todo> todo = todoService.getById(id);
    if(!todo.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found!");
    }
    todoService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Todo deleted successfully!");
  }
}
