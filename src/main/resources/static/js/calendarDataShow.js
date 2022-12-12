function removeAllToDoOnCalendar() {
    var tdList = $("#calendar-body tr td");
    for (var i = 0; i < tdList.length; i++){
        if ($(tdList[i]).children().length > 1){
            $(tdList[i]).children(":gt(0)").remove();
        }
    }
}

function checkToDoOnCalendar(trId, result) {
    var selectorStr = "#td-ct-" + trId.slice(-2);

    if (result == 1){
        $(selectorStr).css('text-decoration', 'line-through');
        $(selectorStr).css('color', '#DADADA');
        $(selectorStr).parent().css('color', '#DADADA');
    } else{
        $(selectorStr).css('text-decoration', 'none');
        $(selectorStr).css('color', '#000000');
        $(selectorStr).parent().css('color', '#000000');

    }
}


function addTodoOnCalendar(result){
    // 기존의 Div를 모두 지운다.
    removeAllToDoOnCalendar()

    for (var i = 0; i < result.length; i++){
        var $div = document.createElement('div');
        $div.className = 'td-item';
        $div.setAttribute('id', 'td-div-' + result[i].todoId);
        // DB의 to_do_content 데이터를 trim, 공백삭제
        var checker = document.createElement('span');
        checker.setAttribute('id', 'td-ck-' + result[i].todoId);
        checker.className = 'checker';
        checker.textContent = "█";
        checker.style.color = result[i].todoColor;
        $div.appendChild(checker);

        var content = document.createElement('span');
        content.setAttribute('id', 'td-ct-' + result[i].todoId);
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