//package com.project.smallbeginjava11.serviceImpl;
//
//
//import com.project.smallbeginjava11.entity.Todo;
//import com.project.smallbeginjava11.service.CalendarService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.text.ParseException;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class CalendarServiceImpl implements CalendarService {
//
//    private final CalendarMapper calendarMapper;
//
//    public List<Todo> getTodoList(Map<String, Object> params){
//        return calendarMapper.getTodoList(params);
//    }
//
//    @Override
//    public void inputTodoList(Map<String, Object> params) throws ParseException {
//        calendarMapper.inputTodoList(params);
//    }
//
//    @Override
//    public void toDoDelete(Map<String, String> params) throws ParseException {
//        calendarMapper.toDoDelete(params);
//    }
//
//    @Override
//    public List<Todo> getTodoListInMonth(Map<String, Object> params) throws ParseException {
//        return calendarMapper.getTodoListInMonth(params);
//    }
//
//    @Override
//    public void editToDo(Map<String, String> params) throws ParseException {
//        calendarMapper.editToDo(params);
//    }
//
//    @Override
//    public String updateToDoState(Map<String, Object> params) throws ParseException {
//        String toDoState =  params.get("toDoState").toString();
//        if (toDoState.equals("1")) {
//            toDoState = "0";
//        }else {
//            toDoState = "1";
//        }
//        params.put("toDoState", toDoState);
//        calendarMapper.updateToDoState(params);
//        return toDoState;
//    }
//
//}
