package com.jvmacedo.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String title;
  private String description;
  private int priority;
  private boolean status;

  public Todo(String title, String description, int priority, boolean status) {
    this.title = title;
    this.description = description;
    this.priority = priority;
    this.status = status;
  }

}
