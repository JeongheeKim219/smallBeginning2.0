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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO_SEQ_GNR")
    @SequenceGenerator(sequenceName = "TODO_ID_SEQ", name = "TODO_SEQ_GNR", allocationSize = 1, initialValue = 1)
    private Long todoId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String todoContent;

    @Column(nullable = false)
    private LocalDateTime plannedTo;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private int todoState;


    @ColumnDefault("'#000000'")
    private String todoColor;

    @Builder
    public Todo(Member member, Long todoId, String todoContent, LocalDateTime plannedTo, int todoState, String todoColor) {
        this.member = member;
        this.todoId = todoId;
        this.todoContent = todoContent;
        this.plannedTo = plannedTo;
        this.todoState = todoState;
        this.todoColor = todoColor;
    }

    public Todo(TodoDto todoDto, Member member) {
        this.member = member;
        this.todoId = todoDto.getTodoId();
        this.todoContent = todoDto.getTodoContent();
        this.plannedTo = todoDto.getPlannedTo();
        this.todoState = todoDto.getTodoState();
        this.todoColor = todoDto.getTodoColor();;

    }

    public Todo(TodoDto todoDto) {
        this.todoId = todoDto.getTodoId();
        this.todoContent = todoDto.getTodoContent();
        this.plannedTo = todoDto.getPlannedTo();
        this.todoState = todoDto.getTodoState();
        this.todoColor = todoDto.getTodoColor();;
    }
}
