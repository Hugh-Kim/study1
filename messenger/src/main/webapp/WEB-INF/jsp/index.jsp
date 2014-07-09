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
<div class="jumbotron">
    <div class="container">
        <h1>페어코딩 메신저</h1>

        <p>채팅하면서 페이코딩을 할 있습니다.</p>

        <form class="input-group" id="userInfo" action="/makeChatRoom">
            <input type="text" class="form-control" id="userName" name="userName">
            <span class="input-group-btn">
                <button class="btn btn-default" id="makeChatRoom" type="button">방만들기</button>
            </span>
        </form>
        <p><a class="btn btn-primary btn-lg">show pair coding rooms</a></p>
    </div>
</div>
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/socket.io.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script type="text/javascript">
    jQuery("#makeChatRoom").bind("click", function(){
        jQuery("#userInfo").submit();
    });

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