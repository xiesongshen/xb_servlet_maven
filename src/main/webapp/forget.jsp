<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
</head>
<script>
    $(function () {

    });
</script>
<body>
<form action="/login/login" method="post">
    用户名：<input type="text" value="admin" name="username" id="username"><br><br>
    密码：<input type="text" value="admin" name="password" id="password"><br><br>

    <img src="/img/getPic" id="img" onclick="getPic()" alt="无法加载"><br><br>
    验证码：<input type="text" name="picCode" id="picCode"><br><br>

    <input type="submit" value="登录">
</form>


</body>
</html>