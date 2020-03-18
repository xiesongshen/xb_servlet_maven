package com.controller;

import com.constants.SysConstant;
import com.entity.User;
import com.service.LoginService;
import com.utils.ImgCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auth admin
 * @date 2020/3/17 16:34
 * @Description
 */
@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {

    protected void getPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        //获取验证码图片
        BufferedImage image = imgCodeUtil.getImage();

        //获取验证码文本内容
        String code = imgCodeUtil.getText();
        //把图片验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_PIC_CODE, code);

        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }
}
