package com.project.smallbeginjava11.controller;

import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.oauth.dto.MemberDto;
import com.project.smallbeginjava11.oauth.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import static com.project.smallbeginjava11.oauth.dto.MemberDto.convertToDto;

@Controller
public class MemberController {

    @Autowired
    private OAuthService oAuthService;

    @GetMapping("/")
    public String getIndex(Principal principal) {
        String uri = "index";
        if (principal != null) {
            uri = "redirect:/member/info";
        }
        return uri;
    }

    @GetMapping("/member/info")
    public String getMemberInfo(Principal principal, Model model) {
        Member member = oAuthService.getMember(Long.valueOf(principal.getName()));
        MemberDto memberDto = convertToDto(member);

        model.addAttribute("member", memberDto);
        return "index";
    }


}