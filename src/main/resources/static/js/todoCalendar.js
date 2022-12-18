function removeAllTodoOnCalendar() {
    var tdList = $("#calendar-body tr td");
    for (var i = 0; i < tdList.length; i++){
        if ($(tdList[i]).children().length > 1){
            $(tdList[i]).children(":gt(0)").remove();
        }
    }
}

function checkTodoOnCalendar(todoId, result, resultStr) {
    console.log(todoId);
    console.log(todoId.length);
    console.log(typeof todoId);
    console.log('result :', result);
    console.log('typeof result :', typeof result);
    todoId = todoId.length < 2? "0" + todoId : todoId;
    var selectorCon = "#td-ct-" + todoId;
    var selectorCkt = "#td-ck-" + todoId;
    console.log('selectorCon :', selectorCon);
    console.log('selectorCkt :', selectorCkt);

    if (result == "1"){
        console.log("1~!");
        $(selectorCon).css('text-decoration', 'line-through');
        $(selectorCon).css('color', '#DADADA');
        $(selectorCon).parent().css('color', '#DADADA');
    } else{
        console.log("0~!");
        $(selectorCon).css('text-decoration', 'none');
        $(selectorCon).css('color', '#000000');
        $(selectorCon).parent().css('color', '#000000');
    }

    $(selectorCkt).attr('data-state', result);
    $(selectorCkt).html(setCheckboxByState(result));
}


function addTodoOnCalendar(result){
    // 기존의 Div를 모두 지운다.
    removeAllTodoOnCalendar()

    for (var i = 0; i < result.length; i++){
        var $div = document.createElement('div');
        var todoStr = String(result[i].todoId);
        var todoId = todoStr.length < 2? "0" + todoStr : todoStr;
        console.log('todoId :', todoId);
        $div.className = 'td-item';
        $div.setAttribute('id', 'td-div-' + todoId);
        // DB의 to_do_content 데이터를 trim, 공백삭제
        var checker = document.createElement('span');
        checker.setAttribute('id', 'td-ck-' + todoId);
        checker.className = 'checker';

        // js 중 todoTable.js의 setCheckboxByState메소드를 활용
        checker.dataset.state = result[i].todoState;
        checker.innerHTML = setCheckboxByState(result[i].todoState);
        $div.appendChild(checker);

        var content = document.createElement('span');
        content.setAttribute('id', 'td-ct-' + todoId);
        content.className = 'content'
        content.textContent = result[i].todoContent.replaceAll(" ", "").trim();

        // state에 따라 다른 css 적용, 이미 완료 상태라면 check 표시
        if (result[i].todoState == 1) {
            content.style.textDecoration = 'line-through';
            content.style.color = '#DADADA';
            $div.style.color = '#DADADA';
        }

        $div.appendChild(content);

        var tdId = "#" + setDateId(result[i].plannedTo);
        $(tdId).append($div);
    }

}