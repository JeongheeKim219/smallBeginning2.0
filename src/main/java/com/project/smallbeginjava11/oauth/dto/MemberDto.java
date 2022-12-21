package com.project.smallbeginjava11.oauth.dto;

import com.project.smallbeginjava11.entity.BaseTimeEntity;
import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.entity.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberDto extends BaseTimeEntity {

    private String email;
    private String nickname;
    private String role;


    public MemberDto update(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public static final MemberDto convertToDto(Member member) {
        return MemberDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .role(Role.MEMBER.getKey())
                .build();
    }

    public static final Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .nickname(memberDto.getNickname())
                .email(memberDto.getEmail())
                .role(Role.MEMBER)
                .build();
    }


}
