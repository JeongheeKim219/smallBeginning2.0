package com.project.smallbeginjava11.entity;

import com.project.smallbeginjava11.oauth.dto.TodoDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String todoContent;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime plannedTo;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private int todoState;

    @Builder
    public Todo(Member member, Long todoId, String todoContent, LocalDateTime plannedTo, int todoState, String todoColor) {
        this.member = member;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.plannedTo = plannedTo;
        this.todoState = todoState;
    }

    public Todo(TodoDto todoDto, Member member) {
        this.member = member;
        this.todoId = todoDto.getTodoId();
        this.todoContent = todoDto.getTodoContent();
        this.plannedTo = todoDto.getPlannedTo();
        this.todoState = todoDto.getTodoState();
    }

    public Todo(TodoDto todoDto) {
        this.todoId = todoDto.getTodoId();
        this.todoContent = todoDto.getTodoContent();
        this.plannedTo = todoDto.getPlannedTo();
        this.todoState = todoDto.getTodoState();
    }
}
