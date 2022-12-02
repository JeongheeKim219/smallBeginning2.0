package com.project.smallbeginjava11.controller;


import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.oauth.dto.MemberDto;
import com.project.smallbeginjava11.oauth.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static com.project.smallbeginjava11.oauth.dto.MemberDto.convertToDto;

@RestController
@RequestMapping("/oauth/login")
public class OAuthController {

	@Autowired
	private OAuthService oAuthService;

	@RequestMapping("/google")
	public ResponseEntity loginWithGoogleOauth2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String authToken = oAuthService.loginOAuthGoogle(request);

		final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
				.httpOnly(true)
				.maxAge(7 * 24 * 3600)
				.path("/")
				.secure(false)
				.build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		response.sendRedirect("http://localhost:8080/oauth/login/user/info");
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user/info")
	public ModelAndView getUserInfo(Principal principal) {
		ModelAndView model = new ModelAndView();
		Member member = oAuthService.getMember(Long.valueOf(principal.getName()));
		MemberDto memberDto = convertToDto(member);
		model.addObject("member", memberDto);
		model.setViewName("index");
		return model;
	}

}