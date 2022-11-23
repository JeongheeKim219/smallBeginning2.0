package com.project.smallbeginjava11.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

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

//    @Column(nullable = false)
//    private Long userId;

    @Column(nullable = false)
    private String todoContent;

    @Column(nullable = false)
    private Date plannedTo;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private int todoState;


    @ColumnDefault("'#000000'")
    private String todoColor;

}
