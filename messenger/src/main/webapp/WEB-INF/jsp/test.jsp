<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<input id="intoChatRoom" type="button" value="입장">
<div id="chatWindow" style="display: none;">
    <div id="comunicateWindow" >
        <textarea rows="50" cols="100" id="chatArea"></textarea>
    </div>
    <div id="writeWindow">
        <textarea rows="5" cols="100" id="writeArea"></textarea>
        <input id="send" type="button" value="보내기">
    </div>
</div>
<script type="text/javascript" >
    jQuery("#intoChatRoom").click(function() {
        jQuery("#chatWindow").show();
    });

    jQuery("#send").click(function() {
       jQuery.ajax({
           type : 'POST',
           url : '/say',
           data : {message : jQuery("#writeArea").val()},
           success : function(data) {
               console.log("success!");
           },
           error : function() {
               alert("comunicate fail.");
           }
       });
    });
</script>
</body>
</html>