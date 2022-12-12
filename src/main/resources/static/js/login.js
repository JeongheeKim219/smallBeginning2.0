

  const clientId = '65473999009-3hch27kdnnd73dmj2uuk5u8t5u28a0mq.apps.googleusercontent.com';
  const redirectUri = 'http://localhost:8080/oauth/login/google';
  var fragmentString = location.hash.substring(1);

  // Parse query string to see if page request is coming from OAuth 2.0 server.
  var params = {};
  var regex = /([^&=]+)=([^&]*)/g, m;
  while (m = regex.exec(fragmentString)) {
    params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
  }
  if (Object.keys(params).length > 0) {
    localStorage.setItem('oauth2-test-params', JSON.stringify(params) );
    if (params['state'] && params['state'] == 'try_sample_request') {
      trySampleRequest();
    }
  }



 function handleCredentialResponse(response) {
//   ("Encoded JWT ID token: " + response.credential);
    console.log(response);
    $.ajax({
                // [요청 시작 부분]
                url: 'http://localhost:8080/oauth/login/google',
                data : {"???": "왜째서"}, //json 데이터
//                data : JSON.stringify(response), //json 데이터
                type: "POST", // 전송 타입
                async: true, // 비동기 여부
                timeout: 5000, // 타임 아웃 설정
                dataType: "JSON", // 응답받을 데이터 타입 (XML,JSON,TEXT,HTML)
                withCredentials: true,
                contentType: "application/json; charset=utf-8", //헤더의 Content-Type을 설정
                beforeSend : function(xhr){ // 다중 헤더 추가 실시
                    xhr.setRequestHeader("Accept", "application/json; charset=utf-8"); //헤더의 Content-Type을 설정
                    xhr.setRequestHeader('Referrer-Policy', 'no-referrer-when-downgrade');
                },

                // [응답 확인 부분]
                success: function(response) {
                    console.log("");
                    console.log("[requestPostBodyJson] : [response] : " + JSON.stringify(response));
                    console.log("");
                },

                // [에러 확인 부분]
                error: function(xhr) {
                    console.log("");
                    console.log("[requestPostBodyJson] : [error] : " + xhr);
                    console.log("");
                },

                // [완료 확인 부분]
                complete:function(data,textStatus) {
                    console.log("");
                    console.log("[requestPostBodyJson] : [complete] : " + textStatus);
                    console.log("");
                }
            });
}

/**
* response
*/
function sendToServer(responseInput) {

    console.log(responseInput.credential);

//    readData('/readData/test', responseInput.credential);

    $.ajax({
            // [요청 시작 부분]
            url: 'http://localhost:8080/oauth/login/google',
            data : JSON.stringify(responseInput), //json 데이터
            type: "POST", // 전송 타입
            async: true, // 비동기 여부
            timeout: 5000, // 타임 아웃 설정
            dataType: "JSON", // 응답받을 데이터 타입 (XML,JSON,TEXT,HTML)
            withCredentials: true,
            contentType: "application/json; charset=utf-8", //헤더의 Content-Type을 설정
            beforeSend : function(xhr){ // 다중 헤더 추가 실시
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8"); //헤더의 Content-Type을 설정
                xhr.setRequestHeader('Referrer-Policy', 'no-referrer-when-downgrade');
            },

            // [응답 확인 부분]
            success: function(response) {
                console.log("");
                console.log("[requestPostBodyJson] : [response] : " + JSON.stringify(response));
                console.log("");
            },

            // [에러 확인 부분]
            error: function(xhr) {
                console.log("");
                console.log("[requestPostBodyJson] : [error] : " + xhr);
                console.log("");
            },

            // [완료 확인 부분]
            complete:function(data,textStatus) {
                console.log("");
                console.log("[requestPostBodyJson] : [complete] : " + textStatus);
                console.log("");
            }
        });

// try {
//    const response = fetch('http://localhost:8080/login/oauth2/code/google', {
//      method: 'POST',
//      credentials: 'include', // include, *same-origin, omit
//      headers: {
//        Accept: 'application/json',
//        'Content-Type': 'application/json',
//        'Referrer-Policy': 'no-referrer-when-downgrade'
//      },
//      body: JSON.stringify(responseInput.credential), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
//    });
//    if (!response.ok) throw new Error('bad server condition');
//    return true;
//  } catch (e) {
//    console.error('postLoginToken Error: ', e.message);
//    return false;
//  }


}




  // If there's an access token, try an API request.
  // Otherwise, start OAuth 2.0 flow.
  function trySampleRequest() {
    var params = JSON.parse(localStorage.getItem('oauth2-test-params'));
    if (params && params['access_token']) {
      var xhr = new XMLHttpRequest();
      xhr.open('GET',
          'https://www.googleapis.com/drive/v3/about?fields=user&' +
          'access_token=' + params['access_token']);
      xhr.onreadystatechange = function (e) {
        if (xhr.readyState === 4 && xhr.status === 200) {
          console.log(xhr.response);
        } else if (xhr.readyState === 4 && xhr.status === 401) {
          // Token invalid, so prompt for user permission.
          oauth2SignIn();
        }
      };
      xhr.send(null);
    } else {
      oauth2SignIn();
    }
  }


/*
   * Create form to request access token from Google's OAuth 2.0 server.
   */
  function oauth2SignIn() {
    // Google's OAuth 2.0 endpoint for requesting an access token
    var oauth2Endpoint = 'https://accounts.google.com/o/oauth2/v2/auth';

    // Create element to open OAuth 2.0 endpoint in new window.
    var form = document.createElement('form');
    form.setAttribute('method', 'GET'); // Send as a GET request.
    form.setAttribute('action', oauth2Endpoint);

    // Parameters to pass to OAuth 2.0 endpoint.
    var params = {'client_id': clientId,
                  'redirect_uri': redirectUri,
                  'include_granted_scopes': 'true',
                  'scope': 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email',
                  'response_type': 'code',
                  'access_type': 'offline',
                  'state': 'state_parameter_passthrough_value'};

    // Add form parameters as hidden input values.
    for (var p in params) {
      var input = document.createElement('input');
      input.setAttribute('type', 'hidden');
      input.setAttribute('name', p);
      input.setAttribute('value', params[p]);
      form.appendChild(input);
    }

    // Add form to page and submit it to open the OAuth 2.0 endpoint.
    document.body.appendChild(form);
    form.submit();
  }