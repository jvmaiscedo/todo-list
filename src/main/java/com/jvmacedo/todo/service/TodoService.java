package com.jvmacedo.todo.service;

import com.jvmacedo.todo.dto.TodoDTO;
import com.jvmacedo.todo.model.Todo;
import com.jvmacedo.todo.repository.TodoRepository;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
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

@Service
public class TodoService {
  @Autowired
  private TodoRepository repository;
  public Todo create (TodoDTO todo){
    Todo newTodo = new Todo(todo.title(), todo.description(), todo.priority(), todo.status());
    return repository.save(newTodo);
  }
  public List<Todo> getByPriority(Integer priority){
    return repository.findByPriority(priority);
  }
  public List<Todo> getByStatus(boolean status){
    return repository.findByStatus(status);
  }
  public List<Todo> getAll(){
    return repository.findAll();
  }
  public Optional<Todo> getById(UUID id){
    return repository.findById(id);
  }
  public Optional<Todo> update(TodoDTO todo, UUID id){
    repository.deleteById(id);
    Todo newTodo = new Todo(todo.title(), todo.description(), todo.priority(), todo.status());
    return Optional.of(repository.save(newTodo));
  }
  public void delete(UUID id){
    repository.deleteById(id);
  }
}
