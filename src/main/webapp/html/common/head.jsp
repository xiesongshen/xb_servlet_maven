<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>头部</title>
</head>
<style>
    #top {
        height: 10%;
        border: 1px solid red;
    }

    #left {
        width: 10%;
        height: 90%;
        border: 1px solid red;
        float: left
    }

    #right {
        width: 90%;
        height: 90%;
        border: 1px solid red;
        float: right;
    }
</style>
<script>
    $(function () {
        $("#head-img").click(function () {
            // 点击头像跳转到用户详情页面
            window.location.href = "/user/detail?id=" + $("#loginUserId").val();
        });
    });
</script>
<body>
<div id="top">
    <div>
        <%--后台以io流的方式返回图片数据--%>
        <img id="head-img" src="/img/getHead?pic=${loginUser.pic}" style="width: 50px;height: 50px;"/>
        <img id="head-img" src="/img/getHead?pic=${sessionScope.get("sessionLoginUser").pic}"
             style="width: 50px;height: 50px;"/>
    </div>
    欢迎：${loginUser.realName}
    <input id="loginUserId" type="hidden" value="${loginUser.id}">

    <a href="/login/logOut">退出</a>
</div>
</body>
</html>