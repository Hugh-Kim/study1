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
    <input type="submit" id="intoChatRoom" value="입장">
</form>
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/socket.io.js"></script>
<script type="text/javascript">
    jQuery("#userInfo").submit(function(e) {
        console.dir(e);
        if (jQuery("#userName").val() == '') {
            alert("이름 넣어주세요.");
            e.preventDefault();
            return;
        }
        jQuery("#userName").val(encodeURIComponent(jQuery("#userName").val()));
    });
</script>
</body>
</html>