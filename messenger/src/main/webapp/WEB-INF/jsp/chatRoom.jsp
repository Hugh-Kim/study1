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
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font: 13px Helvetica, Arial; }
        #wirteSection { background: #000; padding: 3px; position: fixed; bottom: 0; width: 100%; }
        #wirteSection input { border: 0; padding: 10px; width: 90%; margin-right: .5%; }
        #wirteSection button { width: 9%; background: rgb(130, 224, 255); border: none; padding: 10px; }
        #messages { list-style-type: none; margin: 0; padding: 0; }
        #messages li { padding: 5px 10px; }
        #messages li:nth-child(odd) { background: #eee; }
    </style>
</head>
<body>
<ul id="messages"></ul>
<form id="wirteSection">
    <input id="writeMessage" autocomplete="off" /><button id="sendBtn" >Send</button>
</form>
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/socket.io.js"></script>
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