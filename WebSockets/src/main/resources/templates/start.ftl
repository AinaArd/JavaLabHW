<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/css/chat.css"/>
    <title>Chat Application</title>
</head>
<body>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<div class="container">
    <div id="username-page">
        <div class="username-page-container">
            <div class="form-group">
                <a href="/chat/1" type="submit" id="1" name="1">First chat</a>
            </div>
            <br>
            <div class="form-group">
                <a href="/chat/2" type="submit" id="2" name="2">Second chat</a>
            </div>
            <br>
            <div class="form-group">
                <a href="/chat/3" type="submit" id="3" name="3">Third chat</a>
            </div>
            <br>
        </div>
    </div>
</div>
<script src="/webjars/sockjs-client/1.0.0/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script src="/webjars/js-cookie/js.cookie.js"></script>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
</body>
</html>