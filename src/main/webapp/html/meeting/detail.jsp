<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布会议</title>
</head>

<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <c:if test="${map.isNeedJoin==1}">
        <c:if test="${map.flag==1}">
            <input type="button" value="取消会议" onclick="unJoinMeeting()" class="btn btn-danger">
        </c:if>
        <c:if test="${map.flag==2}">
            <input type="button" value="参加会议" onclick="joinMeeting()" class="btn btn-info">
        </c:if>
    </c:if>
    <c:if test="${map.isNeedJoin==2}">
        <input type="button" value="不需要参加会议" class="btn btn-info">
    </c:if>

    <br><br>
    标题：${meeting.title}<br><br>
    部门名称：${meeting.deptName}<br><br>
    应到：${map.shoulds}<br><br>
    实到：${map.realCount}<br><br>
    未到：${map.shoulds-map.realCount}<br><br>
    开始时间：${meeting.startTime}<br><br>
    发布时间：${meeting.publishDate}<br><br>
    会议内容：${meeting.content}<br><br>
</div>
</body>
<script>
    //参加会议
    function joinMeeting() {
        var status =${meeting.status};
        if (status == 1) {
            alert("会议正在进行中！");
            return false;
        }
        if (status == 2) {
            alert("会议已经结束！");
            return false;
        }
        window.location.href = "/meeting/joinMeeting?id=" +${meeting.id};
    }

    //取消会议
    function unJoinMeeting() {
        var status =${meeting.status};
        if (status == 1) {
            alert("会议正在进行中！");
            return false;
        }
        if (status == 2) {
            alert("会议已经结束！");
            return false;
        }
        window.location.href = "/meeting/unJoinMeeting?id=" +${meeting.id};
    }

</script>
</html>