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
        System.out.println("또 와이라노...");
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
//    @RequestMapping(value="/toDoListInsert", produces="text/html;charset=UTF-8")
//    @ResponseBody
//    @PostMapping
//    public String toDoListInsert(@RequestParam Map<String, Object> params) throws ParseException {
//        calendarService.inputTodoList(params);
//        return "success";
//    }
//
//    @Transactional
//    @RequestMapping(value="/toDoDelete", produces="text/html;charset=UTF-8")
//    @ResponseBody
//    @PostMapping
//    public String toDoDelete(@RequestParam Map<String, String> params) throws ParseException {
//        calendarService.toDoDelete(params);
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
//    @RequestMapping(value="/updateToDoState")
//    @ResponseBody
//    @PostMapping
//    public String updateToDoState(@RequestParam Map<String, Object> params) throws ParseException{
//        String toDoState = String.valueOf(calendarService.updateToDoState(params));
//        return toDoState;
//    }
}
