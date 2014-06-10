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
<form id="userInfo" method="post" action="/intoChatRoom">
    <input type="text" id="userName" name="userName">
    <input type="button" id="intoChatRoom" value="입장">
</form>
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/socket.io.js"></script>
<script type="text/javascript">
    jQuery("#intoChatRoom").click(function() {
        jQuery("#userInfo").submit();
    });
</script>
</body>
</html>