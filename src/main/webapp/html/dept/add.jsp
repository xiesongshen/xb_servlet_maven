<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增部门</title>
</head>
<style>
    .error {
        color: red;
    }
</style>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form id="form-add" action="/dept/addDept" method="post">
        部门名称：<input type="text" name="name" value=""><br><br>
        <input type="submit" value="保存">
    </form>

</div>
</body>
<script>
    // 表单验证start
    $.validator.addMethod("checkName", function (value, element, params) {
        // var reg = /^[0-9a-zA-Z]{5,10}$/;
        // if (reg.test(value)) {
        //     return true;
        // } else {
        //     return false;
        // }
        return true;
    });
    $("#form-add").validate({
        rules: {
            name: {
                checkName: ""
            }
        },
        messages: {
            name: {
                checkName: "请输入部门名称！"
            }
        }
    });
    // 表单验证end

</script>
</html>