package com.project.smallbeginjava11.controller;

import org.springframework.stereotype.Controller;
//
@Controller
public class MemberController {


//    private final LoginService loginService;
//
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