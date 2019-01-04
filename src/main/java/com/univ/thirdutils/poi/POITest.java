package com.univ.thirdutils.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/29 5:09 PM
 * @description 使用poi处理excel文件的最基本用法
 */
public class POITest {



    /**
     * 读取excel 2003的版本
     * HSSFWorkbook
     * HSSFSheet
     * HSSFRow
     * HSSFCell
     * @throws IOException
     */
    @Test
    public void read2003() throws IOException {
        File file = new File("data/excel/2003.xls");
        // HSSF没法处理excel 2007的版本，放开下面这句看输出
        // org.apache.poi.poifs.filesystem.OfficeXmlFileException: The supplied data appears to be in the Office 2007+ XML. You are calling the part of POI that deals with OLE2 Office Documents. You need to call a different part of POI to process this data (eg XSSF instead of HSSF)
        // File file = new File("data/excel/2007.xlsx");

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = workbook.getSheetAt(0);

        // 获取行数
        int totalRowNum = sheet.getPhysicalNumberOfRows();
        System.out.println("总行数：" + totalRowNum);
        System.out.println("第一行行号：" + sheet.getFirstRowNum());
        System.out.println("最后一行行号：" + sheet.getLastRowNum());// 从0开始计数

        // 一般第一行存标题，所以从第二行开始
        for (int i = 1; i < totalRowNum; i++) {
            // 获取行
            HSSFRow row = sheet.getRow(i);
            // 直接定义全局NumberFormatException异常
            System.out.println("单元格的内容为：" + row.getCell(0).getStringCellValue());
        }
    }

    /**
     * 读取excel 2007的版本
     * XSSFWorkbook
     * XSSFSheet
     * XSSFRow
     * XSSFCell
     * @throws IOException
     */
    @Test
    public void read2007() throws IOException {
        File file = new File("data/excel/2007.xlsx");
        // XSSF没法处理excel 2002的版本，放开下面这句看输出
        // org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException: The supplied data appears to be in the OLE2 Format. You are calling the part of POI that deals with OOXML (Office Open XML) Documents. You need to call a different part of POI to process this data (eg HSSF instead of XSSF)
        // File file = new File("data/excel/2003.xls");

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);

        // 获取行数
        int totalRowNum = sheet.getPhysicalNumberOfRows();
        System.out.println("总行数：" + totalRowNum);
        System.out.println("第一行行号：" + sheet.getFirstRowNum());
        System.out.println("最后一行行号：" + sheet.getLastRowNum());// 从0开始计数

        // 一般第一行存标题，所以从第二行开始
        for (int i = 1; i < totalRowNum; i++) {
            // 获取行
            XSSFRow row = sheet.getRow(i);
            // 直接定义全局NumberFormatException异常
            System.out.println("单元格的内容为：" + row.getCell(0).getStringCellValue());
        }
    }

    /**
     * 统一解决方案
     *  Workbook
     *  Sheet
     *  Row
     *  Cell
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    public void readExcel() throws IOException, InvalidFormatException {
        File file = new File("data/excel/2007.xlsx");
        // 两者均可处理，放开下面这句看结果
        // File file = new File("data/excel/2003.xls");

        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);

        // 获取行数
        int totalRowNum = sheet.getPhysicalNumberOfRows();
        System.out.println("总行数：" + totalRowNum);
        System.out.println("第一行行号：" + sheet.getFirstRowNum());
        System.out.println("最后一行行号：" + sheet.getLastRowNum());// 从0开始计数

        // 一般第一行存标题，所以从第二行开始
        for (int i = 1; i < totalRowNum; i++) {
            // 获取行
            Row row = sheet.getRow(i);
            // 直接定义全局NumberFormatException异常
            System.out.println("单元格的内容为：" + row.getCell(0).getStringCellValue());
        }
    }
}
