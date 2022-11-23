function addTodo(result){
//    var resultTodo = JSON.parse(result);

    if(resultTodo.length > 0){
        var table4Todo = document.getElementById("toDoListsTable");

        var i=0;
        for(var obj of resultTodo){
            var values = Object.values(obj);

            var newRaw = table4Todo.insertRow();
            newRaw.id = "td-tr-" + obj.toDoCode;
            var color = newRaw.insertCell(0);
            var content = newRaw.insertCell(1);
            var state = newRaw.insertCell(2);
            var deleteBtn = newRaw.insertCell(3);
            var editBtn = newRaw.insertCell(4);

            color.innerText = "■";
            color.id = values[5];
            deleteBtn.id = values[0];
            color.classList.add("color");
            content.classList.add("content");
            deleteBtn.classList.add("delete");
            state.classList.add("state");
            editBtn.classList.add("edit");
            editBtn.classList.add(i);

            content.innerText = values[2];
            state.innerText = values[4];
            deleteBtn.innerText = "delete";
            editBtn.innerText = "  edit";

            var tester = document.getElementById(values[5]);
            var colorTodo = "#" + values[5];
            $(tester).css("color", values[5]);
            i++;
        }
    }
}

function addTodo4edit(result){
//    var resultTodo = JSON.parse(result);

    if(resultTodo.length > 0){
/*        var table4Todo = document.getElementById("toDoListsTable");*/

        var i=0;
        for(var obj of resultTodo){
            var values = Object.values(obj);

            var newRaw = "<tr id='td-tr-" + obj.toDoCode + "'>";
            newRaw += "<td id='" + values[5]+ "' class='color'>■</td>" + "<td class='content'>" + values[2] + "</td>";
            newRaw += "<td class='state'>" + values[4] + "</td>" + "<td class='delete' id='" + values[0] + "'> delete  </td>";
            newRaw += "<td class='edit' class='" + i + "'>  edit</td></tr>";

            $("#toDoListsTable").append(newRaw);

            var tester = document.getElementById(values[5]);
            var colorTodo = "#" + values[5];
            $(tester).css("color", values[5]);
            i++;
        }
    }
}

function editTodo(sendColor, sendContent, clickedDate, toDoCode){
     $.ajax({
             url : "/editToDo",
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : {"toDoCode" : toDoCode, "toDoContent" : sendContent, "toDoColor" : sendColor},
             success : function(result){
                //readToDo(clickedDate);
                window.location.href = "/calendar1";
             },
             error : function(err){
                 console.log(err+"에러발생");
             }
      });
}

// 삭제
function removeTodoTable(){
    var lengthT = $("#toDoListsTable > tbody tr").length;
    if(lengthT > 0){
        var todoT = document.getElementById("toDoListsTable");
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

// Session 값 가져오기
function getSession(){
    var session = $("#session").text();
    // TODO: 테스트용 임시
    if(session == ''){
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/login";
    }else{
        return session;
    }
}