package com.controller;

import com.alibaba.fastjson.JSON;
import com.constants.SysConstant;
import com.service.UserService;
import com.utils.ImgCodeUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auth admin
 * @date 2020/3/17 16:34
 * @Description
 */
@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {

    //服务器保存图片的真实路径前缀
    public static final String URL_PREFIX = "D:\\img\\head\\";

    private UserService userService = new UserService();

    /*
     * @description 修改头像图片
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    protected void uploadHeadImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 把图片上传到服务器
        //2 修改数据库中的路径
        //3 修改session中的loginUser

        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);
        //每个表单域中数据会封装到一个对应的FileItem对象上
        PrintWriter out = response.getWriter();
        try {
            List<FileItem> items = sfu.parseRequest(request);
            String fileName = "";

            //区分表单域
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String name = item.getName();
                    // 获取文件名后缀
                    String suffix = name.substring(name.lastIndexOf("."));

                    //获得文件名，这个新的文件名需要保存到数据库
                    fileName = UUID.randomUUID().toString() + suffix;
                    String path = URL_PREFIX + fileName;
                    File file = new File(path);
                    if (!file.exists()) {
                        //将文件写出到指定磁盘（即保存图片的服务器）
                        item.write(file);
                    }
                }
            }
            // 修改数据库中的pic地址
            userService.updatePic(loginUser.getId(), fileName);

            //修改session中的loginUser的pic
            HttpSession session = request.getSession();
            loginUser.setPic(fileName);
            session.setAttribute(SysConstant.SESSION_LOGIN_USER, loginUser);

            //把文件名返回给前端
            Map dataMap = new HashMap();
            dataMap.put("status", 200);
            dataMap.put("picUrl", fileName);
            out.write(JSON.toJSONString(dataMap));

        } catch (Exception e) {
            Map dataMap = new HashMap();
            dataMap.put("status", 500);
            dataMap.put("message", "图片上传失败");
            out.write(JSON.toJSONString(dataMap));
            e.printStackTrace();
        }
    }

    /*
     * @description 获取头像图片
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    protected void getHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pic = request.getParameter("pic");
        //完整路径=服务器路径+数据库保存的图片名称
        String realUrl = URL_PREFIX + pic;
        File file = new File(realUrl);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while (true) {
            len = bis.read(b);
            if (len == -1) {
                break;
            }
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        bis.close();
    }

    /*
     * @description 获取图片验证码
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
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
