<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
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

    记住我（7天）：<input type="checkbox" name="remember" value="1"><br><br>

    <input type="submit" value="登录">
</form>

<a href="/weChat/wxLogin">微信登录</a>
<a href="/qq/qqLogin">QQ登录</a>
<br><br>

<a href="/register.jsp">注册账号</a>
<a href="/forget.jsp">忘记密码</a>

</body>
<script>
    function getPic() {
        document.getElementById("img").src = document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }
</script>
</html>