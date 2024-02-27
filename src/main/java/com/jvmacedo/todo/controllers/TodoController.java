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
  @GetMapping("/allByUser/{userId}")
  private ResponseEntity<Object> getAll(@PathVariable String userId){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll(userId));
  }
  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable String userID, @PathVariable UUID id){
    Optional<Todo> todo = todoService.getById(userID, id);
    if(!todo.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(todo);
  }
  @GetMapping("/priority/{userId}/{priority}")
  public ResponseEntity<Object> getByPriority(@PathVariable String userId, @PathVariable Integer priority){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.getByPriority(userId, priority));
  }
  @GetMapping("/status/{userId}/{status}")
  public ResponseEntity<Object> getByStatus(@PathVariable String userId, @PathVariable boolean status){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.getByStatus(userId, status));
  }
  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid TodoDTO todoDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todoDTO));
  }
  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody TodoDTO todo){
    return ResponseEntity.status(HttpStatus.OK).body(todoService.update(todo, id));
  }
  @DeleteMapping("/{userId}/{id}")
  public ResponseEntity<Object> delete(@PathVariable String userID, @PathVariable UUID id){
    Optional<Todo> todo = todoService.getById(userID, id);
    if(!todo.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found!");
    }
    todoService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Todo deleted successfully!");
  }
}
