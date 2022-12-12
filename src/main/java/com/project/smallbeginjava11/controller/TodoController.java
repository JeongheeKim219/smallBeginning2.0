package com.project.smallbeginjava11.controller;

import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.oauth.dto.TodoDto;
import com.project.smallbeginjava11.oauth.service.OAuthService;
import com.project.smallbeginjava11.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private OAuthService oAuthService;


    @Transactional
    @RequestMapping(value="/readToDoListOnDate")
    public List<TodoDto> readCalendarOnDate(Principal principal, @RequestParam Map<String, String> params) {
        Long memberId = Long.valueOf(principal.getName());
        List<TodoDto> todoList = todoService.getTodoListOnDate(memberId, params);
        return todoList;
    }

    @Transactional
    @RequestMapping(value="/readToDoInMonth")
    public List<TodoDto> readToDoInMonth(Principal principal, @RequestParam Map<String, String> params) {
        Long memberId = Long.valueOf(principal.getName());
        List<TodoDto> todoList = todoService.getTodoListInMonth(memberId, params);
        return todoList;
    }

    @Transactional
    @RequestMapping(value="/insertTodoList", produces="text/html;charset=UTF-8")
    public String todoListInsert(Principal principal, @RequestParam Map<String, Object> params) {
        Long memberId = Long.valueOf(principal.getName());
        Member member = oAuthService.getMember(memberId);
        todoService.inputTodoList(params, member);
        return "success";
    }

    @Transactional
    @RequestMapping(value="/deleteTodo", produces="text/html;charset=UTF-8")
    public String todoDelete(@RequestParam Map<String, String> params) {
        todoService.deleteTodo(params);
        return "success";
    }

    @RequestMapping(value="/updateTodoContent", produces="text/html;charset=UTF-8")
    public String updateTodoContent(Principal principal, @RequestParam Map<String, String> params) {
        Long memberId = Long.valueOf(principal.getName());
        Member member = oAuthService.getMember(memberId);
        todoService.updateTodoContent(params, member);
        return "success";
    }

    @RequestMapping(value="/updateTodoState", produces="text/html;charset=UTF-8")
    public String updateTodoState(Principal principal, @RequestParam Map<String, String> params) {
        Long memberId = Long.valueOf(principal.getName());
        Member member = oAuthService.getMember(memberId);
        String todoState = String.valueOf(todoService.updateTodoState(params, member));
        return todoState;
    }

}
