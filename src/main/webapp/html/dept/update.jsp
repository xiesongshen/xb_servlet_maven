<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改部门</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form action="/dept/updateDept" method="post">
        <input type="hidden" name="id" value="${dept.id}">
        部门名称：<input type="text" name="name" value="${dept.name}"><br><br>
        <input type="submit" value="保存">
    </form>

</div>
</body>
</html>