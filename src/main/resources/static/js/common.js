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
                 readToDo();
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

function checkToDo(trId, toDoState){
        var toDoCode = trId.slice(-2);

        $.ajax({
            url : '/updateToDoState',
            type : "post",
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            dataType : "text",
            data : {
                "toDoCode" : toDoCode,
                "toDoState" : toDoState
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

function readToDo(clickedDate){
//    var UserCode = getSession();
    if (!clickedDate) clickedDate = getDate4Ajax($(".active").attr("id"));

     $.ajax({
             url : "/readToDoList",
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : {"selectedDate" : clickedDate,
//                     "UserCode" : UserCode
             },
             success : function(result){
                 //기존 테이블 삭제
                 removeTodoTable();

                 // 새로 변경된 테이블 생성 뒤 로드
                 addTodo(result);
             },
             error : function(err){
                 console.log(err+"에러발생");
             }
      });
}

function readToDoInMonth(selectedDate){
    if (!selectedDate) selectedDate = getDate4Ajax($(".active").attr("id"));
//    var UserCode = getSession();
    var selectedMonth = selectedDate.slice(0, 7);

    $.ajax({
        url : "/readToDoInMonth",
        type : "post",
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : "JSON",
        data : {"selectedMonth" : selectedMonth,
//                "UserCode" : UserCode
        },
        success : function(result){
            addTodoOnCalendar(result);
        },
        error : function(err){
            console.log(err+"에러발생");
        }
    });
}

function deleteTodo(deleteid, selectedDate){
      $.ajax({
             url : "/toDoDelete",
             type : "post",
             contentType: 'application/x-www-form-urlencoded; charset=utf-8',
             dataType : "text",
             data : {"toDoCode" : deleteid},
             success : function(result){
                 readToDo(selectedDate);
             },
             error : function(err){
                 console.log(err+"error");
             }
      });
}