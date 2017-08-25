package com.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 生成exel文件的工具类
 */
public class BuildExcelUtils {
    /**
     * @description 生成excel头部样式
     * @author lichunhui
     * @param wb
     * @param bold
     * @param fontName
     * @param isItalic
     * @param hight
     * @return Font
     */
    private org.apache.poi.ss.usermodel.Font createTitleFont(Workbook wb, short bold, String fontName, boolean isItalic, short hight) {
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setBoldweight(bold);
        font.setItalic(isItalic);
        font.setFontHeight(hight);
        return font;
    }
    /**
     * @description 给每个单元格加入边框样式
     * @author lichunhui
     * @param cellStyle void
     */
    private void addCellBorder(CellStyle cellStyle) {
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
    }
    public HSSFWorkbook builExcel(Map<String, Object> jsonMap) throws ParseException {
        /** 创建工作簿 */
        HSSFWorkbook wb = new HSSFWorkbook();
        String endDate = jsonMap.get("endDate").toString();


        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

       // Calendar calendar = Calendar.getInstance();
        //calendar.setTime(simpleDateFormat.parse(endDate));

        /** 创建excel中的sheet */
        Sheet sheet = wb.createSheet("excel模板");

        /** 创建行（表头行） */
        Row row = sheet.createRow(0);
        /** 设置行高 */
        row.setHeightInPoints((short) 30);
        /** 区域合并 */
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 12));

        return wb;
    }
}
