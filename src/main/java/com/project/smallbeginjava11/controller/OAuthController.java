package com.project.smallbeginjava11.controller;


import com.project.smallbeginjava11.oauth.service.OAuthService;
import com.project.smallbeginjava11.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

	@Autowired
	private OAuthService oAuthService;

	@Autowired
	private TodoService todoService;

	@RequestMapping("/login/google")
	public ResponseEntity loginWithGoogleOauth(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String authToken = oAuthService.loginOAuthGoogle(request);

		final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
				.httpOnly(true)
				.maxAge(7 * 24 * 3600)
				.path("/")
				.secure(false)
				.build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		response.sendRedirect("https://a1.smallbeginning.today");
		return ResponseEntity.ok().build();
	}


}