const clientId = '65473999009-3hch27kdnnd73dmj2uuk5u8t5u28a0mq.apps.googleusercontent.com';
const redirectUri = 'http://129.154.55.56:22/oauth/login/google';

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