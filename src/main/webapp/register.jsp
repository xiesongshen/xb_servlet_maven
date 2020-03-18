<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="${path}/static/js/jquery.js"></script>
</head>
<script>

    $(function () {
        $("#username").blur(function () {
            var name = $("#username").val();
            $.ajax({
                url: "/user/checkUserName",
                type: "get",
                data: {name: name},
                dataType: "text",
                success: function (data) {
                    if (data == 500) {
                        alert("该账号已经注册！");
                        $("#name-flag").val(0);
                    } else {
                        $("#name-flag").val(1);
                    }
                }
            });
        });

        $("#email").blur(function () {
            var email = $("#email").val();
            $.ajax({
                url: "/user/checkEmail",
                type: "get",
                data: {email: email},
                dataType: "text",
                success: function (data) {
                    if (data == 500) {
                        alert("该邮箱已经注册！");
                        $("#email-flag").val(0);
                    } else {
                        $("#email-flag").val(1);
                    }
                }
            });
        });

        $("#rePassword").blur(function () {
            var password = $("#password").val();
            var rePassword = $("#rePassword").val();
            if (password != rePassword) {
                alert("两次密码不一致，请重新输入密码！");
                $("#password-flag").val(0);
            } else {
                $("#password-flag").val(1);
            }
        });

        $("#btn").click(function () {
            if ($("#name-flag").val() == 0 || $("#email-flag").val() == 0 || $("#password-flag").val() == 0) {
                return false;
            } else {
                $("#form").submit();
            }
        });
    });
</script>
<body>
<form id="form" action="/user/register" method="post">
    用户名：<input type="text" value="" name="username" id="username"><br><br>
    密码：<input type="text" value="" name="password" id="password"><br><br>
    邮箱：<input type="text" value="" name="email" id="email"><br><br>
    确认密码：<input type="text" value="" name="rePassword" id="rePassword"><br><br>
    <input type="button" id="btn" value="注册">

    <%-- 标识表单是否可以提交 0:不可以 1:可以 --%>
    <input type="hidden" id="name-flag" value="0">
    <input type="hidden" id="email-flag" value="0">
    <input type="hidden" id="password-flag" value="0">

</form>
</body>

</html>