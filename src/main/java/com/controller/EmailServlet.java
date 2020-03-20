package com.controller;

import com.constants.SysConstant;
import com.utils.EmailUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author admin
 * @version 1.0.1
 * @company
 * @date 2019/12/2 10:39
 * @description 发送邮件
 */
@WebServlet("/email/*")
public class EmailServlet extends BaseServlet {

    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取邮箱地址
        String emailName = request.getParameter("email");

        //生成随机的4位验证码
        int code = (int) ((Math.random() + 1) * 1000);

        //发送验证码
        EmailUtil.sendEmail(emailName, String.valueOf(code));

        //把验证码存到session中  （mysql id ,code）（redis key code）
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_EMAIL_CODE_NAME, code);
        //设置有效时间60秒
        session.setMaxInactiveInterval(60);

        PrintWriter out = response.getWriter();
        out.append("发送邮件成功！");
    }
}
