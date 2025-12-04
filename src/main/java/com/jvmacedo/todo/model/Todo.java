package com.jvmacedo.todo.model;

import jakarta.persistence.*;
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
  private String userId;
  private String title;
  private String description;
  private int priority;
  private boolean status;

  public Todo(String userId, String title, String description, int priority, boolean status) {
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.priority = priority;
    this.status = status;
  }

}
