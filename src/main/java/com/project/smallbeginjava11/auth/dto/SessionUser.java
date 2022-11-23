package com.project.smallbeginjava11.auth.dto;

import com.project.smallbeginjava11.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String nickname;
    private String email;

    public SessionUser(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
