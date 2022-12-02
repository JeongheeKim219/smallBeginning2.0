package com.project.smallbeginjava11.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Setter
@Table(name = "Member")
public class Member extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GNR")
    @SequenceGenerator(sequenceName = "USER_ID_SEQ", name = "USER_SEQ_GNR", allocationSize = 1, initialValue = 1)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ColumnDefault("'1'")
    private int activeState;

    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }


    public Member update(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getRole() {
        return this.role.getKey();
    }

}
