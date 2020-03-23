package com.service;

import com.dao.MeetingDao;
import com.entity.Meeting;
import com.entity.Page;
import com.enums.StatusEnum;
import com.utils.DateUtil;

import java.util.Arrays;
import java.util.Date;
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

    /*
     * @description 添加会议
     * @author admin
     * @param [meeting]
     * @return void
     */
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
     * @description 根据会议id查询实到人数
     * @author admin
     * @date 2020/3/23
     * @param [meetingId]
     * @return java.lang.Integer
     */
    public Integer getMeetingCountByMeetingId(Integer meetingId) {
        return meetingDao.getMeetingCountByMeetingId(meetingId);
    }

    /*
     * @description 检查当前用户是否需要参加会议
     * @author admin
     * @date 2020/3/23
     * @param [userId, meetingId]
     * @return java.lang.Integer
     */
    public Integer checkJoinMeeting(Integer userId, Integer meetingId) {
        //已经参加会议
        if (meetingDao.checkJoinMeeting(userId, meetingId) > 0) {
            return 1;
        } else {
            //未参加会议
            return 2;
        }
    }

    /*
     * @description参加会议
     * @author admin
     * @date 2020/3/23
     * @param [userId, meetingId]
     * @return void
     */
    public void joinMeeting(Integer userId, Integer meetingId) {
        meetingDao.joinMeeting(userId, meetingId);
    }

    /*
     * @description取消会议
     * @author admin
     * @date 2020/3/23
     * @param [userId, meetingId]
     * @return void
     */
    public void unJoinMeeting(Integer userId, Integer meetingId) {
        meetingDao.unJoinMeeting(userId, meetingId);
    }

    /*
     * @description 定时任务修改会议状态
     * @author admin
     * @date 2020/3/23
     * @param []
     * @return void
     */
    public void updateStatusTask() {
        List<Meeting> list = meetingDao.listAll();
        for (Meeting meeting : list) {
            //当前时间戳
            long currentTime = new Date().getTime();
            long startTime = DateUtil.getTimeByStr(meeting.getStartTime());
            long endTime = DateUtil.getTimeByStr(meeting.getEndTime());

            if (startTime <= currentTime) {
                if (endTime > currentTime) {
                    //会议正在进行中
                    meetingDao.updateStatus(meeting.getId(), StatusEnum.DOING.getValue());
                } else {
                    //会议已经结束
                    meetingDao.updateStatus(meeting.getId(), StatusEnum.END.getValue());
                }
            } else {
                //会议未开始，不需要处理
            }

        }
    }
}
