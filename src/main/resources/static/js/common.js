function readData(url, data){
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


function readToDoForm(formId, url){
      $.ajax({
             url : url,
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : $(formId).serialize(),
             success : function(result){
                 readToDoInMonth();
             },
             error : function(err){
                 console.log(err+"에러발생");
             }
      });
}

function sendDateList(startDate, endDate, dateList, url){
    $.ajax({
         url : url,
         type : "post",
         contentType: 'application/x-www-form-urlencoded; charset=utf-8',
         dataType : "text",
         data : {
                "dateList" : dateList
                },
         success : function(result){
         },
         error : function(err){
             console.log("sendDateList error");
         }
    });

};

function checkToDo(trId, todoState){
        var todoId = trId.slice(-2);
        console.log(todoId)

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
                checkToDoInTable(trId, result);
                checkToDoOnCalendar(trId, result);
            },
            error : function(err, resp){
                console.log(err+"에러발생");
            }
        });
}

function readToDoOnDate(clickedDate){
    if (!clickedDate) clickedDate = getDate4Ajax($(".active").attr("id"));

     $.ajax({
             url : "/readToDoListOnDate",
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : {"selectedDate" : clickedDate,
             },
             success : function(result){
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

function readToDoInMonth(selectedDate){
//    if (!selectedDate) selectedDate = getDate4Ajax($(".active").attr("id"));
    if (!selectedDate) selectedDate = dateToString(new Date());
    var selectedMonth = selectedDate.slice(0, 7);
    var pointDate = new Date(selectedDate);
    var lastDayOfMonth = dateToString(getLastDayOfMonth(pointDate));
    var firstDayOfMonth = selectedMonth + "-01";

    $.ajax({
        url : "/readToDoInMonth",
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
      $.ajax({
             url : "/deleteTodo",
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : {
                "todoId": todoId
             },
             success : function(result){
                 readToDoInMonth(selectedDate);
             },
             error : function(err){
                 console.log(err+"error");
             }
      });
}