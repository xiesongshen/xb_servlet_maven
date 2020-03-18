<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script>
    $(function () {
        alert(123);
    });

</script>
<body>
<form action="/login/login" method="post">
    用户名：<input type="text" value="admin" name="username" id="username"><br><br>
    密码：<input type="text" value="admin" name="password" id="password"><br><br>
    验证码：<input type="text" name="picCode" id="picCode"><br><br>
    <img src="/img/getPic" id="img" onclick="getPic()" alt="无法加载">
    <input type="submit" value="登录">
    <a href="/register.jsp">注册账号</a>
</form>
</body>
<script>
    function getPic() {
        document.getElementById("img").src = document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }

</script>
</html>