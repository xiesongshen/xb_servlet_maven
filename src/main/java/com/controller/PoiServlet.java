package com.controller;

import com.entity.User;
import com.service.PoiService;
import com.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @auth admin
 * @date 2020/3/20 16:06
 * @Description
 */
@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet {

    private PoiService poiService = new PoiService();

    private UserService userService = new UserService();

    /*
     * @description
     * @author admin
     * @date 2020/3/20
     * @param [request, response]
     * @return void
     */
    protected void userExportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String name = request.getParameter("name");
        name = name == null ? "" : name;

        User condition = new User();
        condition.setUsername(name);

        Workbook wb = poiService.userExportExcel(condition);
        //方式1 直接写出到本地
        // FileOutputStream fos = new FileOutputStream("D:\\用户列表Excel.xls");
        // wb.write(fos);

        //方式2 将excel实例以流的形式写回浏览器
        downloadExcel(response, wb, "用户列表Excel.xls");
    }

    /*
     * @description 下载用户列表模板
     * @author admin
     * @date 2020/3/20
     * @param [request, response]
     * @return void
     */
    protected void userDownLoadTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        //第一种方式 生成一个excel
        Workbook wb = poiService.userDownLoadTemplate();
        downloadExcel(response, wb, "用户列表模板Excel.xls");

        //第二种方式
    }

    /*
     * @description 导入Excel到数据库
     * @author admin
     * @date 2020/3/20
     * @param [request, response]
     * @return void
     */
    protected void userImportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        //核心Api
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //判断enctype是否是muitipart/form-data类型
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("表单的enctype属性不是multipart/form-data类型");
        }
        //设置单个文件上传大小 2M
        fileUpload.setFileSizeMax(2 * 1024 * 1024);
        //设置总上传文件大小(有时候一次性上传多个文件，需要有一个上限,此处为10M)
        fileUpload.setSizeMax(10 * 1024 * 1024);

        //解析请求
        try {
            List<FileItem> parseRequest = fileUpload.parseRequest(request);
            //获取数据
            for (FileItem fileItem : parseRequest) {
                //判断数据类型是不是普通的form表单字段
                if (!fileItem.isFormField()) {
                    //获取文件名称
                    String fileName = fileItem.getName();
                    //截取后缀
                    String[] arr = fileName.split("\\.");
                    String suffix = arr[arr.length - 1];
                    //根据上传的excel文件构造workbook实例-注意区分xls与xlsx版本对应的实例
                    InputStream fileStream = fileItem.getInputStream();
                    Workbook wb = poiService.getWorkbook(fileStream, suffix);
                    //读取上传上来的excel的数据到List<User>中
                    List<User> list = poiService.getExcelData(wb);
                    //插入到数据库
                    for (User user : list) {
                        userService.add(user);
                    }
                    fileStream.close();
                } else {
                    //普通字段
                    //字段名
                    String fieldName = fileItem.getFieldName();
                    //字段值
                    String fieldValue = fileItem.getString();
                    System.out.println(fieldName + ":" + fieldValue);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

    public void downloadExcel(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }

}
