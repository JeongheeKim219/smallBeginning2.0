package com.project.smallbeginjava11.controller;

//import com.project.smallbeginjava11.service.CalendarService;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class IndexController {
//
//    private final CalendarService calendarService;

    @RequestMapping("/index")
    public String Index(@RequestParam @Nullable Map<String, String> param){
        return "index";
    }
    //
//    @Transactional
//    @RequestMapping(value="/readToDoList")
//    @ResponseBody
//    public List<Todo> readCalendar(@RequestParam Map<String, Object> param) {
//        List<Todo> todo = calendarService.getTodoList(param);
//        return todo;
//    }
//
//    @Transactional
//    @RequestMapping(value="/readToDoInMonth")
//    @ResponseBody
//    public List<Todo> readToDoInMonth(@RequestParam Map<String, Object> param) throws ParseException {
//        String selectedMonth = String.valueOf(param.get("selectedMonth"));
//        List<Todo> todoList = calendarService.getTodoListInMonth(param);
//        return todoList;
//    }
//
//    @Transactional
//    @RequestMapping(value="/todoListInsert", produces="text/html;charset=UTF-8")
//    @ResponseBody
//    @PostMapping
//    public String todoListInsert(@RequestParam Map<String, Object> params) throws ParseException {
//        calendarService.inputTodoList(params);
//        return "success";
//    }
//
//    @Transactional
//    @RequestMapping(value="/todoDelete", produces="text/html;charset=UTF-8")
//    @ResponseBody
//    @PostMapping
//    public String todoDelete(@RequestParam Map<String, String> params) throws ParseException {
//        calendarService.todoDelete(params);
//        return "success";
//    }
//
//    @Transactional
//    @RequestMapping(value="/editToDo", produces="text/html;charset=UTF-8")
//    @ResponseBody
//    @PostMapping
//    public String editToDo(@RequestParam Map<String, String> params) throws ParseException {
//        calendarService.editToDo(params);
//        return "success";
//    }
//
//    @RequestMapping(value="/updateTodoState")
//    @ResponseBody
//    @PostMapping
//    public String updateTodoState(@RequestParam Map<String, Object> params) throws ParseException{
//        String todoState = String.valueOf(calendarService.updateTodoState(params));
//        return todoState;
//    }
}
