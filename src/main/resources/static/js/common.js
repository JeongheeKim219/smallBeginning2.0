function readData(url, data){
if (isAuthenticated != "true") return;

    $.ajax({
        url : url,
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : {
            "data" : data
        },
        success : function(result){
         console.log(result);
        },
        error : function(err){
         console.log(err+"에러발생");
        }
    });
}

function readForm(formId, url, callback, flag){
if (isAuthenticated != "true") return;

    $.ajax({
        url : url,
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : $(formId).serialize(),
        success : function(result){
            if (callback != null && callback != undefined) {
                if (flag) {
                    callback(result);
                } else {
                    callback();
                }
            }
        },
        error : function(err){
            console.log(err+"에러발생");
        }
    });
}


function readTodoForm(formId, url){
if (isAuthenticated != "true") return;

    $.ajax({
        url : url,
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : $(formId).serialize(),
        success : function(result){
            readTodoInMonth();
        },
        error : function(err){
            console.log(err+"에러발생");
        }
    });
}


function checkTodo(trId, todoState){
    if (isAuthenticated != "true") return;
    var todoId = trId.slice(6);

    $.ajax({
        url : '/updateTodoState',
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : {
            "todoId" : todoId,
            "todoState" : todoState
        },
        success : function(result){
            checkTodoInTable(todoId, result);
            checkTodoOnCalendar(todoId, result);
        },
        error : function(err, resp){
            console.log(err+"에러발생");
        }
    });
}

function readTodoOnDate(clickedDate){
    if (isAuthenticated != "true") return;

    if (!clickedDate) {
        if (!$(".active").attr("id")) {
            clickedDate = dateToString(new Date());
        } else {
            clickedDate = getDate4Ajax($(".active").attr("id"));
        }
    }

    $.ajax({
        url : "/readTodoListOnDate",
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : {
            "selectedDate" : clickedDate,
        },
        success : function(result){
            console.log(result);
            //기존 테이블 삭제
            removeTodoTable();
            // 새로 변경된 테이블 생성 뒤 로드
            addTodoListTable(result);
        },
        error : function(err){
            console.log(err+"에러발생");
        }
    });
}

function readTodoInMonth(selectedDate){
    if (isAuthenticated != "true") return;

    if (!selectedDate) selectedDate = dateToString(new Date());
    var selectedMonth = selectedDate.slice(0, 7);
    var pointDate = new Date(selectedDate);
    var lastDayOfMonth = dateToString(getLastDayOfMonth(pointDate));
    var firstDayOfMonth = selectedMonth + "-01";

    $.ajax({
        url : "/readTodoInMonth",
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "JSON",
        data : {
            "firstDayOfMonth" : firstDayOfMonth,
            "lastDayOfMonth" : lastDayOfMonth
        },
        success : function(result){
            console.log(result)
            addTodoOnCalendar(result);
        },
        error : function(err){
            console.log(err+"에러발생");
        }
    });
}

function deleteTodo(todoId, selectedDate){
    if (isAuthenticated != "true") return;

    $.ajax({
        url : "/deleteTodo",
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : {
            "todoId": todoId
        },
        success : function(result){
            readTodoOnDate(selectedDate);
            readTodoInMonth(selectedDate);
        },
        error : function(err){
            console.log(err+"error");
        }
    });
}

function editTodo(sendContent, clickedDate, todoId){
    if (isAuthenticated != "true") return;

    $.ajax({
        url : "/updateTodoContent",
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "text",
        data : {
            "todoId" : todoId,
            "todoContent" : sendContent
        },
        success : function(result){
            readTodoInMonth(getDate4Ajax(clickedDate));
            readTodoOnDate(getDate4Ajax(clickedDate));
            return false;
        },
        error : function(err){
            console.log(err+"에러발생");
            return false;
        }
    });
}