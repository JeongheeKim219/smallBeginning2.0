package com.project.smallbeginjava11.service;

import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.entity.Todo;
import com.project.smallbeginjava11.oauth.dto.TodoDto;
import com.project.smallbeginjava11.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).toFormatter();

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDto> getTodoListOnDate(Long memberId, Map<String, String> params) {
        String selectedDateStr = params.get("selectedDate");

        LocalDateTime selectedDate = LocalDateTime.parse(selectedDateStr, formatter);
        return todoRepository.findByMemberMemberIdAndPlannedToOrderByCreatedAtDesc(memberId, selectedDate).stream()
                 .map(TodoDto::new)
                 .collect(Collectors.toList());
    }

    public List<TodoDto> getTodoListInMonth(Long memberId, Map<String, String> params) {
        String fromStr = params.get("firstDayOfMonth");
        String toStr = params.get("lastDayOfMonth");

        LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
        LocalDateTime to = LocalDateTime.parse(toStr, formatter);

        return todoRepository.findByMemberMemberIdAndPlannedToBetweenOrderByCreatedAtDesc(memberId, from, to).stream()
                 .map(TodoDto::new)
                 .collect(Collectors.toList());
    }

    public void inputTodoList(Map<String, Object> params, Member member) {
        Todo todo = mapToTodo(params, member);
        todoRepository.save(todo);
    };

    public Todo mapToTodo(Map<String, Object> map, Member member) {
        String todoContent = String.valueOf(map.get("todoContent"));
        String plannedToStr = String.valueOf(map.get("plannedTo"));
        LocalDateTime plannedTo = LocalDateTime.parse(plannedToStr, formatter);
        String todoColor = String.valueOf(map.get("todoColor"));

        Todo todo = Todo.builder()
                        .member(member)
                        .todoContent(todoContent)
                        .plannedTo(plannedTo)
                        .todoState(0)
                        .build();
        return todo;
    }

    public void deleteTodo(Map<String, String> params) {
        todoRepository.deleteById(Long.valueOf(params.get("todoId")));
    };

    public TodoDto getTodoDto(Long todoId) {
        Todo todo = todoRepository.findById(Long.valueOf(todoId)).orElse(null);
        return new TodoDto(todo);
    };

    public int updateTodoState(Map<String, String> params, Member member) {
        Long todoId = Long.valueOf(params.get("todoId"));
        TodoDto todoDto = getTodoDto(todoId);

        int changeTodoState = changeTodoState(params);
        todoDto.setTodoState(changeTodoState);

        Todo todo = todoRepository.save(new Todo(todoDto, member));
        return todo.getTodoState();
    };

    public int changeTodoState(Map<String, String> params) {
        int changedState = 0;
        int todoState = Integer.parseInt(params.get("todoState"));
        if (todoState == 0) changedState = 1;
        return changedState;
    }

    public void updateTodoContent(Map<String, String> params, Member member) {
        Long todoId = Long.valueOf(params.get("todoId"));
        TodoDto todoDto = getTodoDto(todoId);

        String todoContent = params.get("todoContent");
        todoDto.setTodoContent(todoContent);

        todoRepository.save(new Todo(todoDto, member));
    };

}