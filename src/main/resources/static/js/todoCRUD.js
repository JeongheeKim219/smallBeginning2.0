function addTodoListTable(result){
    var resultTodo = JSON.parse(result);
    console.log(resultTodo);

    if(resultTodo.length > 0){
        var table4Todo = document.getElementById("todoListsTable");

        var i=0;
        for(var obj of resultTodo){

            todoId = "";
            if (obj.todoId < 10) {
                todoId += "0"
            }

            todoId += obj.todoId;

            var newRaw = table4Todo.insertRow();
            newRaw.id = "td-tr-" + todoId;
            var state = newRaw.insertCell(0);
            var content = newRaw.insertCell(1);
            var deleteBtn = newRaw.insertCell(2);
            var editBtn = newRaw.insertCell(3);

            state.innerText = obj.todoState;
            deleteBtn.id = todoId;
            content.classList.add("content");
            deleteBtn.classList.add("delete");
            state.classList.add("state");
            editBtn.classList.add("edit");
            editBtn.classList.add(i);

            content.innerText = obj.todoContent;
            state.innerText = obj.todoState;
            deleteBtn.innerText = "delete";
            editBtn.innerText = "  edit";
            i++;
        }
    }
}

function addTodoListTable4edit(result){
//    var resultTodo = JSON.parse(result);
    var resultTodo = result;
    if(resultTodo.length > 0){
/*        var table4Todo = document.getElementById("todoListsTable");*/

        var i=0;
        for(var obj of resultTodo){
            var values = Object.values(obj);

            var newRaw = "<tr id='td-tr-" + obj.todoId + "'>";
            newRaw += "<td id='" + values[5]+ "' class='color'>■</td>" + "<td class='content'>" + values[2] + "</td>";
            newRaw += "<td class='state'>" + values[4] + "</td>" + "<td class='delete' id='" + values[0] + "'> delete  </td>";
            newRaw += "<td class='edit' class='" + i + "'>  edit</td></tr>";

            $("#todoListsTable").append(newRaw);

            var tester = document.getElementById(values[5]);
            var colorTodo = "#" + values[5];
            $(tester).css("color", values[5]);
            i++;
        }
    }
}

function editTodo(sendContent, clickedDate, todoId){
     $.ajax({
             url : "/updateTodoContent",
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : {
                    "todoId" : todoId,
                    "todoContent" : sendContent},
             success : function(result){
                readToDoInMonth(getDate4Ajax(clickedDate));
                readToDoOnDate(getDate4Ajax(clickedDate));
             },
             error : function(err){
                 console.log(err+"에러발생");
             }
      });
}

// 삭제
function removeTodoTable(){
    var lengthT = $("#todoListsTable > tbody tr").length;
    if(lengthT > 0){
        var todoT = document.getElementById("todoListsTable");
        for(var i=lengthT-1; i>0; i--){
           var test = todoT.deleteRow(i);
        }
    }
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

function checkToDoInTable(trId, result) {
    var selectorStr = "#" + trId + " .state";
    $(selectorStr).text(result);
}
