package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.Meeting;
import com.entity.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.MeetingService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @auth admin
 * @date 2020/3/17 16:34
 * @Description
 */
@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {

    private MeetingService meetingService = new MeetingService();

    /*
     * @description 查询会议（分页+查询条件）
     * @author admin
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询条件
        String title = request.getParameter("title");
        String statusStr = request.getParameter("status");
        title = title == null ? "" : title;
        Integer status = (statusStr == null || statusStr == "") ? null : Integer.valueOf(statusStr);

        //当前页
        String pageStr = request.getParameter("page");
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);

        //构造查询条件对象
        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setStatus(status);

        //总记录数
        Integer count = meetingService.count(meeting);

        Page page = new Page();
        page.setPageCurrent(pageCurrent);
        page.setCount(count);

        //数据
        List<Meeting> list = meetingService.listPage(meeting, page);
        request.setAttribute("list", list);
        request.setAttribute("meeting", meeting);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/html/meeting/list.jsp").forward(request, response);
    }

    /*
     * @description 发布会议
     * @author admin
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    public void addMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        Map<String, String[]> map = request.getParameterMap();
        Meeting meeting = new Meeting();
        BeanUtils.populate(meeting, map);
        meeting.setCreateBy(loginUser.getId());
        meetingService.addMeeting(meeting);
        response.sendRedirect("/meeting/list");
    }

    /*
     * @description 会议详情
     * @author admin
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    public void getMeetingById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("id");
        if (StringUtils.isBlank(idStr)) {
            return;
        }
        Integer meetingId = Integer.valueOf(idStr);
        Meeting meeting = meetingService.getMeetingById(meetingId);
        //应到人数
        String[] shoulds = meeting.getMakeUser().split(",");
        // 实到人数
        Integer realCount = meetingService.getMeetingCountByMeetingId(meetingId);
        // 查看我是否有参加过这个会议
        boolean flag = meetingService.checkJoinMeeting(loginUser.getId(), meetingId) > 0 ? true : false;

        request.setAttribute("meeting", meeting);
        request.setAttribute("shoulds", shoulds.length);
        request.setAttribute("realCount", realCount);
        request.setAttribute("flag", flag);
        request.getRequestDispatcher("/html/meeting/detail.jsp").forward(request, response);
    }

    /**
     * 参加会议
     */
    public void joinMeeting(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("id");
        if (StringUtils.isBlank(idStr)) {
            return;
        }
        Integer meetingId = Integer.valueOf(idStr);
        meetingService.joinMeeting(loginUser.getId(), meetingId);
        response.sendRedirect("/meeting/getMeetingById?id=" + meetingId);
    }

    /**
     * 取消会议
     */
    public void unJoinMeeting(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("id");
        if (StringUtils.isBlank(idStr)) {
            return;
        }
        Integer meetingId = Integer.valueOf(idStr);
        meetingService.unJoinMeeting(loginUser.getId(), meetingId);
        response.sendRedirect("/meeting/getMeetingById?id=" + meetingId);
    }
}
