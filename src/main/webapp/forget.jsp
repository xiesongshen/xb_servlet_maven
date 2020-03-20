<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
</head>
<script>

    var time = 60;
    //定时器
    var dsq;

    $(function () {
        //发送验证码
        $("#btn-send").click(function () {

            dsq = window.setInterval("changeTime()", 1000);
            $("#btn-send").attr("disabled", "disabled");

            var email = $("#email").val();
            $.ajax({
                url: "/email/sendEmail",
                data: {email: email},
                type: "get",
                dataType: "text",
                success: function (data) {
                    alert(data);
                }
            });

        });
    });

    function changeTime() {
        --time;
        $("#time").text(time);
        if (time == 0) {
            $("#time").text("");
            time = 60;
            window.clearInterval(dsq);
            $("#btn-send").attr("disabled", null);
        }
    }
</script>
<body>

<form action="/user/forgetPassword" method="post">
    用户名：<input type="text" value="" name="username" id="username"><br><br>
    新密码：<input type="text" value="" name="password" id="password"><br><br>
    邮箱：<input type="text" id="email">
    <input type="button" id="btn-send" value="发送验证码">
    <span id="time"></span><br><br>
    验证码：<input type="text" name="code" id="code"><br><br>

    <input type="submit" value="重置">
</form>

</body>
</html>