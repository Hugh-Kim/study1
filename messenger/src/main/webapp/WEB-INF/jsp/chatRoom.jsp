<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-06-10
  Time: 오후 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Socket.IO chat</title>
</head>
<body>
<div id="container">
    <div class="col-lg-3" id="chatWindow">
        <div>
            <ul id="messages"></ul>
        </div>
        <form class="input-group" style="position: fixed; width: inherit;bottom: 0;">
            <input type="text" class="form-control" id="writeMessage">
                <span class="input-group-btn">
                    <button class="btn btn-default" id="sendBtn" type="submit">Send</button>
                </span>

        </form>
    </div>
    <div class="col-lg-9" style="background-color: slategrey">
        <h2>소스코드 편집창</h2>

    </div>
</div>

<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/socket.io.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script type="text/javascript">
    jQuery(function() {
        var socket = io.connect("http://localhost:" + "${port}");
        socket.emit('echo', "welecome, " + "${userName}" + "^^");
        socket.on('connect', function(){
            console.log('connected');
        });

        socket.on('echo', function(msg){
            jQuery("#messages").append(jQuery("<li>").text(msg.data));
        });

        socket.on('chatMessage', function(msg){
            console.dir(msg);
            jQuery("#messages").append(jQuery("<li>").text(msg.userName + " : " +msg.message));
        });

        jQuery("#sendBtn").click(function() {
            jQuery.ajax({
                url : '/send',
                type : 'POST',
                data : {'userName' : '${userName}','message' : jQuery("#writeMessage").val()},
                success : function(data) {
                    console.dir(data);
                    jQuery("#writeMessage").val("");
                },
                error : function() {
                    alert("comunicate error!");
                }
            });
            return false;
        });
    });

</script>
</body>
</html>