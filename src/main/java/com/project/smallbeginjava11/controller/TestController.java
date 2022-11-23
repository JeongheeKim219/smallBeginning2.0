package com.project.smallbeginjava11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "pass";
    }

}
