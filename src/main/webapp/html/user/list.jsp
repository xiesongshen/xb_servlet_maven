<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>
<div id="right">
    <form method="post" action="/user/list">
        用户名：<input type="text" name="username" value="${user.username}">
        <input type="submit" value="查询" class="btn btn-primary">
    </form>

    <a href="/poi/userExportExcel?name=${user.username}" class="btn btn-primary">导出Excel</a>
    <a href="/poi/userDownLoadTemplate" class="btn btn-primary">下载模板</a>
    <br><br>

    <form action="/poi/userImportExcel" method="post" enctype="multipart/form-data">
        <%--导入数据--%>
        <input type="file" name="userExcel">
        <input type="submit" value="导入数据" class="btn btn-primary"/>
    </form>

    <table class="table table-bordered">
        <tr>
            <td>编号</td>
            <td>id</td>
            <td>用户名</td>
            <td>真实姓名</td>
            <td>性别</td>
            <td>年龄</td>
            <td>创建时间</td>
            <td>创建人</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${list}" varStatus="status" var="user">
            <tr>
                <td>${status.index+1}</td>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.realName}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.sex==1}">男</c:when>
                        <c:when test="${user.sex==0}">女</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </td>
                <td>${user.age}</td>
                <td>
                    <fmt:parseDate var="createTime" value="${user.createTime}"
                                   pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>${user.createName}</td>
                <td></td>
            </tr>
        </c:forEach>

    </table>

    <a href="/user/list?page=1&username=${user.username}">首页</a>
    <a href="/user/list?page=${page.pageCurrent-1<=0?1:page.pageCurrent-1}&username=${user.username}">上一页</a>
    <a href="/user/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&username=${user.username}">下一页</a>
    <a href="/user/list?page=${page.pageCount}&username=${user.username}">尾页</a>
    当前页：${page.pageCurrent},共 ${page.pageCount}页,,共${page.count}条

</div>
</body>
</html>