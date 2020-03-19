<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询部门</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>
<div id="right">

    <form method="post" action="/dept/list">
        部门名称：<input type="text" name="name" value="${dept.name}">
        <input type="submit" value="查询" class="btn btn-primary">
    </form>

    <a href="/html/dept/add.jsp" class="btn btn-primary">添加</a>

    <table class="table table-bordered">
        <tr>
            <td>编号</td>
            <td>部门名称</td>
            <td>部门人数</td>
            <td>创建时间</td>
            <td>创建人</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${list}" varStatus="status" var="dept">
            <tr>
                <td>${status.index+1}</td>
                <td>${dept.name}</td>
                <td>${dept.num}</td>
                <td>
                    <fmt:parseDate var="createTime" value="${dept.createTime}"
                                   pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>${dept.createName}</td>
                <td>
                    <a href="/dept/deleteById?id=${dept.id}" class="btn btn-danger">删除</a>
                    <a href="/dept/toUpdateDept?id=${dept.id}" class="btn btn-success">修改</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <a href="/dept/list?page=1&name=${dept.name}">首页</a>
    <a href="/dept/list?page=${page.pageCurrent-1<=0?1:page.pageCurrent-1}&name=${dept.name}">上一页</a>
    <a href="/dept/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&name=${dept.name}">下一页</a>
    <a href="/dept/list?page=${page.pageCount}&name=${dept.name}">尾页</a>
    当前页:${page.pageCurrent},共 ${page.pageCount}页,共${page.count}条

</div>
</body>
</html>