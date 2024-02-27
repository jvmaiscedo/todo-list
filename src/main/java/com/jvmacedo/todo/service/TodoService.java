package com.jvmacedo.todo.service;

import com.jvmacedo.todo.dto.TodoDTO;
import com.jvmacedo.todo.model.Todo;
import com.jvmacedo.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoService {
  @Autowired
  private TodoRepository repository;
  public Todo create (TodoDTO todo){
    Todo newTodo = new Todo(todo.userId(), todo.title(), todo.description(), todo.priority(), todo.status());
    return repository.save(newTodo);
  }
  public List<Todo> getByPriority(String userId, Integer priority){
    return repository.findAllByUserId(userId)
                      .stream()
                      .filter(todo -> todo.getPriority()==priority)
                      .collect(Collectors.toList());
  }
  public List<Todo> getByStatus(String userId, boolean status){
    return repository.findAllByUserId(userId)
      .stream()
      .filter(todo -> todo.isStatus()==status)
      .collect(Collectors.toList());
  }
  public List<Todo> getAll(String userId){
    return repository.findAllByUserId(userId);
  }
  public Optional<Todo> getById(String userId, UUID id){
    return repository.findAllByUserId(userId)
      .stream()
      .findAny()
      .filter(todo -> todo.getId().equals(id));
  }
  public Optional<Todo> update(TodoDTO todo, UUID id){
    repository.deleteById(id);
    Todo newTodo = new Todo(todo.userId(), todo.title(), todo.description(), todo.priority(), todo.status());
    return Optional.of(repository.save(newTodo));
  }
  public void delete(UUID id){
    repository.deleteById(id);
  }
}
