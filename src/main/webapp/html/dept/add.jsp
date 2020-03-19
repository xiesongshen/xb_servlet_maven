<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增部门</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form action="/dept/addDept" method="post">
        部门名称：<input type="text" name="name" value=""><br><br>
        <input type="submit" value="保存">
    </form>

</div>
</body>
</html>