package com.jvmacedo.todo.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TodoDTO(@NotBlank String userId, @NotBlank(message = "Title is mandatory") String title, @ NotBlank(message = "Description is mandatory")String description, int priority, boolean status) {
}
