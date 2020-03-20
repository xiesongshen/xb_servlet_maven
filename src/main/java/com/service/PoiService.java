package com.service;

import com.dao.UserDao;
import com.entity.User;
import com.utils.DateUtil;
import com.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth admin
 * @date 2020/3/20 16:13
 * @Description
 */
public class PoiService {

    private UserDao userDao = new UserDao();

    /*
     * @description导出用户数据到Excel(单sheet)
     * @author admin
     * @date 2020/3/20
     * @param [condition]
     * @return org.apache.poi.ss.usermodel.Workbook
     */
    public Workbook userExportExcel(User condition) {
        String[] headers = new String[]{"用户名", "真实姓名", "性别", "年龄", "创建时间", "创建人"};
        Workbook wb = new HSSFWorkbook();
        //sheet名称
        Sheet sheet = wb.createSheet("用户信息列表");
        //创建sheet的第一行数据-即excel的表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        List<User> list = userDao.listForExcel(condition);
        Row row;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(list.get(i).getUsername() == null ? "" : list.get(i).getUsername());
            row.createCell(1).setCellValue(list.get(i).getRealName() == null ? "" : list.get(i).getRealName());
            String sex = "";
            if (list.get(i).getSex() != null) {
                if (list.get(i).getSex() == 1) {
                    sex = "男";
                } else if (list.get(i).getSex() == 0) {
                    sex = "女";
                }
            }
            row.createCell(2).setCellValue(sex);
            String age = list.get(i).getAge() == null ? "" : list.get(i).getAge().toString();
            row.createCell(3).setCellValue(age);
            row.createCell(4).setCellValue(list.get(i).getCreateTime() == null ? "" : list.get(i).getCreateTime());
            row.createCell(5).setCellValue(list.get(i).getCreateName() == null ? "" : list.get(i).getCreateName());
        }
        return wb;
    }

    public Workbook userDownLoadTemplate() {
        String[] headers = new String[]{"用户名", "真实姓名", "性别", "年龄", "创建时间", "创建人"};
        Workbook wb = new HSSFWorkbook();
        //sheet名称
        Sheet sheet = wb.createSheet("用户信息列表模板");
        //创建表头即可
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        return wb;
    }

    /**
     * @param [inputStream, suffix]
     * @return org.apache.poi.ss.usermodel.Workbook
     * @description 根据file与后缀名区分获取workbook实例
     */
    public Workbook getWorkbook(InputStream inputStream, String suffix) throws Exception {
        Workbook wk = null;
        //后缀名结尾是xls的是2003版本workBook，后缀名是xlsx结尾的是2007版本workBook
        if ("xls".equalsIgnoreCase(suffix)) {
            wk = new HSSFWorkbook(inputStream);
        } else if ("xlsx".equalsIgnoreCase(suffix)) {
            wk = new XSSFWorkbook(inputStream);
        }
        return wk;
    }

    /**
     * @param [wb]
     * @return
     * @description 读取Excel中的数据
     */
    public List<User> getExcelData(Workbook wb) throws Exception {
        List<User> list = new ArrayList<>();
        Row row;
        User user;
        int numSheet = wb.getNumberOfSheets();
        if (numSheet > 0) {
            for (int i = 0; i < numSheet; i++) {
                //获取每个sheet
                Sheet sheet = wb.getSheetAt(i);
                //获取总行数
                int numRow = sheet.getLastRowNum();
                if (numRow > 0) {
                    for (int j = 1; j <= numRow; j++) {
                        //跳过excel sheet表格头部
                        row = sheet.getRow(j);
                        user = new User();
                        //读取每行的每个单元格数据，注入User中
                        // "用户名", "真实姓名", "性别", "年龄", "创建时间", "创建人"
                        user.setUsername(ExcelUtil.manageCell(row.getCell(0), null));
                        user.setRealName(ExcelUtil.manageCell(row.getCell(1), null));
                        String sex = ExcelUtil.manageCell(row.getCell(2), null);
                        user.setSex("男".equals(sex) ? 1 : 0);
                        user.setAge(Integer.valueOf(ExcelUtil.manageCell(row.getCell(3), null)));
                        // user.setCreateTime(ExcelUtil.manageCell(row.getCell(4), null));
                        user.setCreateTime(DateUtil.getDateStr());
                        user.setCreateBy(1);
                        list.add(user);
                    }
                }
            }
        }

        return list;
    }

}
