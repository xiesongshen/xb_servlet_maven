<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布会议</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form action="/meeting/addMeeting" method="post">
        标题：<input type="text" name="title"><br><br>
        选择部门：
        <select name="deptId" id="deptId" class="selectpicker" data-live-search="true">
        </select><br><br>
        抄送人：
        <select multiple name="makeUsers" id="makeUsers" class="selectpicker" data-live-search="true">
        </select><br><br>
        开始时间：<input type="datetime-local" id="startTime" name="startTime"><br><br>
        结束时间：<input type="datetime-local" id="endTime" name="endTime"><br><br>
        会议内容：<textarea class="form-control" rows="5" name="content"></textarea><br><br>

        <input type="submit" value="发布">
    </form>

</div>
</body>
<script>
    $(function () {
        $.ajax({
            url: "/dept/listAll",
            type: "get",
            data: {},
            dataType: "json",
            success: function (data) {
                var html = '<option>请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html = html + '<option value="' + data[i].id + '" >' + data[i].name + '</option>';
                }
                $("#deptId").append(html);
                // 刷新页面UI
                $('#deptId').selectpicker('refresh');
            }
        });

        $("#deptId").change(function () {
            var deptId = $(this).val();
            if (deptId == null) {
                return false;
            }
            $.ajax({
                url: "/user/findUserByDeptId",
                type: "get",
                data: {deptId: deptId},
                dataType: "json",
                success: function (data) {
                    var html = '<option>请选择</option>';
                    for (var i = 0; i < data.length; i++) {
                        html = html + '<option value="' + data[i].id + '" >' + data[i].realName + '</option>';
                    }
                    $("#makeUsers").append(html);
                    // 刷新页面UI
                    $('#makeUsers').selectpicker('refresh');
                }
            });
        });

    });

</script>
</html>