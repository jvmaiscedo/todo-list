package com.jvmacedo.todo.repository;

import com.jvmacedo.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
public interface TodoRepository extends JpaRepository<Todo, UUID> {
  List<Todo> findByPriority(int priority);
  List<Todo> findByStatus(boolean status);


}
