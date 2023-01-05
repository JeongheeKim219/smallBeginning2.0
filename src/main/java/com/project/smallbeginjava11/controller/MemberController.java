package com.project.smallbeginjava11.controller;

import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.oauth.dto.MemberDto;
import com.project.smallbeginjava11.oauth.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static com.project.smallbeginjava11.oauth.dto.MemberDto.convertToDto;

@Controller
public class MemberController {

    @Autowired
    private OAuthService oAuthService;

    @GetMapping("/")
    public ModelAndView getIndex(Principal principal) {
        ModelAndView model = new ModelAndView();
        model.addObject("isAuthenticated", false);
        model.setViewName("index");
        if (principal != null) {
            Member member = oAuthService.getMember(Long.valueOf(principal.getName()));
            MemberDto memberDto = convertToDto(member);
            model.addObject("member", memberDto);
            model.addObject("isAuthenticated", true);
        } else {
            model.addObject("member", null);
        }
        return model ;
    }

}