package com.project.smallbeginjava11.oauth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.entity.Role;
import com.project.smallbeginjava11.oauth.config.JWTUtils;
import com.project.smallbeginjava11.oauth.dto.GoogleOAuthTokenDto;
import com.project.smallbeginjava11.oauth.dto.MemberDto;
import com.project.smallbeginjava11.oauth.dto.OAuthAttributes;
import com.project.smallbeginjava11.oauth.property.GoogleOAuthProperties;
import com.project.smallbeginjava11.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.project.smallbeginjava11.oauth.dto.MemberDto.convertToDto;
import static com.project.smallbeginjava11.oauth.dto.MemberDto.toEntity;

@Service
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final JWTUtils jwtUtils;
    private final GoogleIdTokenVerifier verifier;
    private final HttpSession httpSession;

    @Autowired
    private final GoogleOAuthProperties googleOAuthProperties;

    public OAuthService(@Value("${app.googleClientId}") String clientId, MemberRepository memberRepository,
                        JWTUtils jwtUtils, HttpSession httpSession, GoogleOAuthProperties googleOAuthProperties) {
        this.memberRepository = memberRepository;
        this.jwtUtils = jwtUtils;
        this.httpSession = httpSession;
        this.googleOAuthProperties = googleOAuthProperties;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public String loginOAuthGoogle(HttpServletRequest request) throws Exception {
        GoogleOAuthTokenDto tokenDto = exchangeCodeToToken(request);

        Member member = verifyIDToken(tokenDto.getIdToken());
        if (member == null) {
            throw new IllegalArgumentException();
        }
        member = createOrUpdateMember(member);
        return jwtUtils.createToken(member, false);
    }

    public GoogleOAuthTokenDto exchangeCodeToToken(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> parameters = setParameter(code);

        HttpEntity<MultiValueMap<String,String>> restRequest = new HttpEntity<>(parameters, headers);

        URI uri = URI.create("https://oauth2.googleapis.com/token");
        ResponseEntity<GoogleOAuthTokenDto> restResponse = restTemplate.postForEntity(uri, restRequest, GoogleOAuthTokenDto.class);
        GoogleOAuthTokenDto tokenDto = restResponse.getBody();

        return tokenDto;
    }

    public MultiValueMap<String, String> setParameter(String code) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("code", code);
        parameters.add("client_id", googleOAuthProperties.getClientId());
        parameters.add("client_secret", googleOAuthProperties.getClientSecret());
        parameters.add("redirect_uri", googleOAuthProperties.getRedirectUri());
        parameters.add("access_type", googleOAuthProperties.getAccessType());
        parameters.add("grant_type", "authorization_code");

        return parameters;
    }

    @Transactional
    public Member createOrUpdateMember(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
        if (existingMember == null) {
            MemberDto memberDto = convertToDto(existingMember);
            return memberRepository.save(toEntity(memberDto));;
        }
        MemberDto memberDto = convertToDto(existingMember);
        memberDto.setNickname(member.getNickname());
        memberDto.setEmail(member.getEmail());
        return memberRepository.save(toEntity(memberDto));
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
            e.printStackTrace();
            return null;
        }
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .orElse(attributes.toEntity())
                .update(attributes.getName());
        return memberRepository.save(member);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("member", member);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

}
