package com.project.smallbeginjava11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;
//
@Controller
@RequiredArgsConstructor
public class MemberController {
//
//    private final LoginService loginService;
//
    @GetMapping("/snsLogin")
    public String toSnsLogin(){
        return "snsLogin";
    }
//
//    @GetMapping("/logout")
//    public ModelAndView logout(HttpServletRequest request){
//        ModelAndView modelAndView = new ModelAndView("login");
//
//        HttpSession session = request.getSession();
//        if(session.getAttribute("memberCode") != null) {
//            session.removeAttribute("memberCode");
//        }
//
//        return modelAndView;
//    }
//
//    @Transactional
//    @RequestMapping(value="/loginProcess", produces="text/html;charset=UTF-8")
//    @PostMapping
//    @ResponseBody
//    public String loginProcess(@RequestBody Map<String, String> params, HttpServletRequest request, Model model) throws ParseException {
//        HttpSession session = request.getSession();
//
//        if(session.getAttribute("memberCode") != null) {
//            session.removeAttribute("memberCode");
//        }
//
//        String result = loginService.loginProcess(params);
//
//        if(result == null){
//            return "failed";
//        }else{
//            session.setAttribute("memberCode", result);
//            return "/calendar1";
//        }
//    }
}