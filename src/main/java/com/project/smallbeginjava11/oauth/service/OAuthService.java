package com.project.smallbeginjava11.oauth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.entity.Role;
import com.project.smallbeginjava11.oauth.config.JWTUtils;
import com.project.smallbeginjava11.oauth.dto.IdTokenRequestDto;
import com.project.smallbeginjava11.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JWTUtils jwtUtils;
    private final GoogleIdTokenVerifier verifier;

    public MemberService(@Value("${app.googleClientId}") String clientId, MemberRepository memberRepository,
                          JWTUtils jwtUtils) {
        this.memberRepository = memberRepository;
        this.jwtUtils = jwtUtils;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public String loginOAuthGoogle(IdTokenRequestDto requestBody) {
        Member member = verifyIDToken(requestBody.getIdToken());
        if (member == null) {
            throw new IllegalArgumentException();
        }
        member = createOrUpdateUser(member);
        return jwtUtils.createToken(member, false);
    }

    @Transactional
    public Member createOrUpdateUser(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
        if (existingMember == null) {
            member.setRole(Role.MEMBER);
            memberRepository.save(member);
            return member;
        }
        existingMember.setNickname(member.getNickname());
        existingMember.setEmail(member.getEmail());
        memberRepository.save(existingMember);
        return existingMember;
    }

    private Member verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String email = payload.getEmail();
            String nickname = (String) payload.get("name");

            return new Member(email, nickname);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}
