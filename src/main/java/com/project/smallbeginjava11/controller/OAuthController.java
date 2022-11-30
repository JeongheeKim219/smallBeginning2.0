package com.project.smallbeginjava11.controller;


import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class OAuthController {
//	private OAuthService oAuthService;
//	private AuthTokenProvider jwtManager;

	@RequestMapping(value =  "/login/oauth2/code/google", method = RequestMethod.GET)
	public void googleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		System.out.println("code :" + code);


//		String accessToken = oAuthService.getKakaoAccessToken(code);
//		AuthToken jwtToken = oAuthService.getKakaoUserInfo(accessToken);
//		System.out.println("jwtToken : " + jwtToken.getToken());
//		return jwtToken;
	}

	@RequestMapping(value = "/oauth2callback", method = RequestMethod.GET)
	public void googleLoginCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		for (String reqKey : request.getParameterMap().keySet()){
			System.out.println("key :" + reqKey + ", " + request.getParameterMap().get(reqKey));
		}
		System.out.println(response.toString());

	}
}