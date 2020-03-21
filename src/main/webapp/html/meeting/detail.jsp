<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议详情</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">
    <c:if test="${flag}">
        <input type="button" value="取消会议" onclick="unJoinMeeting()" class="btn btn-danger">
    </c:if>
    <c:if test="${!flag}">
        <input type="button" value="参加会议" onclick="joinMeeting()" class="btn btn-info">
    </c:if>
    标题：${meeting.title}<br><br>
    部门：${meeting.deptName}<br><br>
    应到：${shoulds}<br><br>
    实到：${realCount}<br><br>
    未到：${shoulds-realCount}<br><br>
    开始时间：${meeting.startTime}<br><br>
    发布时间：${meeting.publishDate}<br><br>
    会议内容：${meeting.content}<br><br>
</div>
</body>
<script>

    //参加会议
    function joinMeeting() {
        if (${meeting.status==1}) {
            alert('不可操作!会议正在进行中!')
            return;
        }
        if (${meeting.status==2}) {
            alert('不可操作!已经结束!')
            return;
        }
        window.location.href = "/meeting/joinMeeting?id=${meeting.id}";
    }

    //取消会议
    function unJoinMeeting() {
        if (${meeting.status==1}) {
            alert('不可操作!会议正在进行中!')
            return;
        }
        if (${meeting.status==2}) {
            alert('不可操作!已经结束!')
            return;
        }
        window.location.href = "/meeting/unJoinMeeting?id=${meeting.id}";
    }

</script>
</html>