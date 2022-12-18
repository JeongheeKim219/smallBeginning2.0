function setCheckboxByState(todoState){
    var innerStr = "<input type='checkbox' name='todoState'";
    innerStr += " value='" + todoState + "'";
    if (todoState == "1") innerStr += " checked";
    innerStr += ">";

    return innerStr;
}

function addTodoListTable(result){
    var table4Todo = document.getElementById("todoListTable");
    var resultTodo = JSON.parse(result);
    if(resultTodo.length > 0){
        for(var obj of resultTodo){
            var todoId = "";
            if (obj.todoId < 10) {
                todoId += "0"
            }
            todoId += obj.todoId;
            var todoState = obj.todoState;

            var newRaw = table4Todo.insertRow();
            newRaw.id = "td-tr-" + todoId;
            var state = newRaw.insertCell(0);
            var content = newRaw.insertCell(1);
            var editBtn = newRaw.insertCell(2);
            var deleteBtn = newRaw.insertCell(3);

            content.classList.add("content");
            deleteBtn.classList.add("delete");
            state.classList.add("state");
            editBtn.classList.add("edit");

            content.innerText = obj.todoContent;
            state.innerHTML = setCheckboxByState(todoState);
            state.dataset.state = todoState;
            deleteBtn.innerText = "delete";
            editBtn.innerText = "edit";
        }
    }
}

// 수정
function editTodoTable(node){
    var tr = node.parentNode;
    var content = $(tr).children(".content");
    var complete = $(tr).children(".edit")

    complete.attr("class", "complete")
    complete.text("submit");

    content.attr("class", "editingContent");
    content.attr("contentEditable", "true");
    content.focus();

    return true;
}

// 삭제
function removeTodoTable(){
    $("#todoListTable").html("");
}

function getDate4Ajax(idStr){
    if(idStr != ""){
        dateStr = "";
        dateStr += idStr.slice(0, 4);
        dateStr += "-";
        dateStr += idStr.slice(4, 6);
        dateStr += "-";
        dateStr += idStr.slice(-2);
        return dateStr;
    }
}

function checkTodoInTable(todoId, result){
    var trId = "td-tr-" + todoId;
    var selectorStr = "#" + trId + " .state";
    console.log('checkTodoInTable-----------');
    console.log('selectorStr :', selectorStr);
    console.log('result :', result);
    $(selectorStr).attr('data-state', result)
    $(selectorStr).html(setCheckboxByState(result));
}