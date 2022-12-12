package com.project.smallbeginjava11.oauth.dto;

import com.project.smallbeginjava11.entity.BaseTimeEntity;
import com.project.smallbeginjava11.entity.Todo;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoDto extends BaseTimeEntity {

    private Long todoId;
    private String todoContent;
    private LocalDateTime plannedTo;
    private int todoState;
    private String todoColor;
    private LocalDateTime createdAt;

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }

    public void setPlannedTo(LocalDateTime plannedTo) {
        this.plannedTo = plannedTo;
    }

    public void setTodoState(int todoState) {
        this.todoState = todoState;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setTodoColor(String todoColor) {
        this.todoColor = todoColor;
    }

    public TodoDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.todoContent = todo.getTodoContent();
        this.plannedTo = todo.getPlannedTo();
        this.todoState = todo.getTodoState();
        this.todoColor = todo.getTodoColor();
        this.createdAt = todo.getCreatedAt();
    }

}
