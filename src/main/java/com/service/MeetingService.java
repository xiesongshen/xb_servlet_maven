package com.service;

import com.dao.MeetingDao;
import com.entity.Meeting;
import com.entity.Page;
import com.utils.DateUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @auth admin
 * @date 2020/3/19 16:22
 * @Description
 */
public class MeetingService {

    private MeetingDao meetingDao = new MeetingDao();

    public List<Meeting> listPage(Meeting meeting, Page page) {
        return meetingDao.listPage(meeting, page);
    }

    public Integer count(Meeting meeting) {
        return meetingDao.count(meeting);
    }

    public void addMeeting(Meeting meeting) {
        meeting.setMakeUser(Arrays.toString(meeting.getMakeUsers()));
        meeting.setStatus(0);
        meeting.setPublishDate(DateUtil.getDateStr());
        meeting.setCreateTime(DateUtil.getDateStr());
        meetingDao.addMeeting(meeting);
    }

    public Meeting getMeetingById(Integer id) {
        return meetingDao.getMeetingById(id);
    }

    /*
     * @description 根据会议id查询此会议有多少人参加了
     * @author admin
     * @date 2020/3/21
     * @param [meetingId]
     * @return java.lang.Integer
     */
    public Integer getMeetingCountByMeetingId(Integer meetingId) {
        return meetingDao.getMeetingCountByMeetingId(meetingId);
    }

    /*
     * @description 根据用户id和会议id查询是否有参加过
     * @author admin
     * @date 2020/3/21
     * @param [userId, meetingId]
     * @return java.lang.Integer
     */
    public Integer checkJoinMeeting(Integer userId, Integer meetingId) {
        return meetingDao.checkJoinMeeting(userId, meetingId);
    }

    /**
     * 参加会议
     */
    public void joinMeeting(Integer userId, Integer meetingId) {
        meetingDao.joinMeeting(userId, meetingId);
    }

    /**
     * 取消会议
     */
    public void unJoinMeeting(Integer userId, Integer meetingId) {
        meetingDao.unJoinMeeting(userId, meetingId);
    }

}
