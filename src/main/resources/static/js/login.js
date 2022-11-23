/*
 * Create form to request access token from Google's OAuth 2.0 server.
 */
var redirectUri = 'http://localhost:8080/login/oauth2/code/google'
function oauthSignIn() {
  // Google's OAuth 2.0 endpoint for requesting an access token
//  var oauth2Endpoint = 'https://accounts.google.com/o/oauth2/v2/auth';
  var oauth2Endpoint = 'http://localhost:8080/oauth2/authorization/google';


  // Create <form> element to submit parameters to OAuth 2.0 endpoint.
  var form = document.createElement('form');
  form.setAttribute('method', 'GET'); // Send as a GET request.
  form.setAttribute('action', oauth2Endpoint);

  // Parameters to pass to OAuth 2.0 endpoint.
  var params = {'client_id': '65473999009-3hch27kdnnd73dmj2uuk5u8t5u28a0mq.apps.googleusercontent.com',
                'redirect_uri': redirectUri,
                'response_type': 'code',
                'include_granted_scopes': 'true',
                'state':'state_parameter_passthrough_value',
                'scope': 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email',
                'prompt': 'select_account'};

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