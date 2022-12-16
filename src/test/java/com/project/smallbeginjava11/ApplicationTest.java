package com.project.smallbeginjava11;

import com.project.smallbeginjava11.entity.Member;
import com.project.smallbeginjava11.entity.Todo;
import com.project.smallbeginjava11.oauth.dto.TodoDto;
import com.project.smallbeginjava11.repository.MemberRepository;
import com.project.smallbeginjava11.repository.TodoRepository;
import com.project.smallbeginjava11.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.smallbeginjava11.oauth.dto.MemberDto.convertToDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SmallBeginJava11Application.class)
public class ApplicationTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TodoService todoService;

    DateTimeFormatter format = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).toFormatter();

    @Test
    public void contextLoads() {
    }


    @Test
    public void 월별_조회() {
        String fromStr = "2022-12-01";
        String toStr = "2022-12-31";

        LocalDateTime from = LocalDateTime.parse(fromStr, format);
        LocalDateTime to = LocalDateTime.parse(toStr, format);

        List<Todo> todoList =  todoRepository.findByMemberMemberIdAndPlannedToBetweenOrderByCreatedAtDesc(Long.valueOf(1), from, to);
        todoList.forEach(x -> System.out.println(x));
    }

    @Test
    public void 월별_조회_service() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("firstDayOfMonth", "2022-12-01");
        params.put("lastDayOfMonth", "2022-12-31");

        List<TodoDto> todoList =  todoService.getTodoListInMonth(Long.valueOf(1), params);
        todoList.forEach(x -> System.out.println(x));
    }

    @Test
    public void 일별_조회() {
        String selectedDateStr = "2022-12-31";
        LocalDateTime selectedDate = LocalDateTime.parse(selectedDateStr, format);

        List<Todo> todoList1 =  todoRepository.findByMemberMemberIdAndPlannedToOrderByCreatedAtDesc(Long.valueOf(1), selectedDate);
        todoList1.forEach(x -> System.out.println(x));

    }

    @Test
    public void 상태_업데이트() {
        TodoDto todoDto = todoService.getTodoDto(Long.valueOf(4));
        Member member = memberRepository.findById(Long.valueOf(1)).orElse(null);

        todoDto.setTodoState(1);
        todoRepository.save(new Todo(todoDto, member));
    }

    @Test
    public void 내용_업데이트() {
        TodoDto todoDto = todoService.getTodoDto(Long.valueOf(6));
        Member member = memberRepository.findById(Long.valueOf(1)).orElse(null);

        todoDto.setTodoContent("컨텐츠 내용 변경용 입력");
        todoRepository.save(new Todo(todoDto, member));
    }

    @Test
    public void 전체_회원_조회() {
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(x -> System.out.println(convertToDto(x)));
    }

    @Test
    public void 특정_회원_삭제() {
        memberRepository.deleteById(Long.valueOf(21));
    }

}
