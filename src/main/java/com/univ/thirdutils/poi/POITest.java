package com.univ.thirdutils.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        DataFormatter formatter = new DataFormatter();
        // 一般第一行存标题，所以从第二行开始
        for (int i = 1; i < totalRowNum; i++) {
            // 获取行
            HSSFRow row = sheet.getRow(i);
            // 直接定义全局NumberFormatException异常
            System.out.println("单元格的内容为：" + row.getCell(0).getStringCellValue());
        }
        /*
        从数字单元格中读取内容
        核心：每个单元格都有类型！
        如果单元格中的内容为数字，则不能调用这里的getStringCellValue方法，否则抛出异常Cannot get a STRING value from a NUMERIC cell
        解决方法：使用DataFormatter
            DataFormatter formatter = new DataFormatter();
            formatter.formatCellValue(row.getCell(0))
        启示：如果只是简单的读取excel的数据，统一使用DataFormatter的方式
         */
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

    @Test
    public void writeBasicOps() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row1 = sheet.createRow(0);
        Cell c1 = row1.createCell(0);
        c1.setCellValue("aaa");

        Cell c2 = row1.createCell(1);
        c2.setCellValue("bbb");

        Row row2 = sheet.createRow(1);
        Cell c21 = row2.createCell(0);
        c21.setCellValue("ccc");

        Cell c22 = row2.createCell(1);
        c22.setCellValue("ddd");

        FileOutputStream outputStream = new FileOutputStream("output.xlsx");
        workbook.write(outputStream);
        // 关闭workbook，释放资源
        workbook.close();
    }

    @Test
    public void writeToExcel() {
        List<Person> personList = Arrays.asList(
                new Person("aaa", 10),
                new Person("bbb", 20),
                new Person("ccc", 30)
        );
        // 转成List<List<Object>>
        List<List<Object>> tableData = new ArrayList<>();
        // important：赋值顺序需要和表头保持一致
        personList.forEach(person -> {
            List<Object> list = new ArrayList<>();
            list.add(person.getName());
            list.add(person.getAge());
            tableData.add(list);
        });
        generateExcelData(Arrays.asList("名字", "年龄"), tableData);
    }

    /**
     * 重点：
     *  1. 表格数据必须使用List<List<Object>>，而不能是List<Person>等形式，因为此时无法逐个单元格迭代；
     *      即必须将List<Person>等形式转成List<List<Object>>的形式；
     *  2. header中表头的值必须和List<Object>中值的顺序是一样的；
     * @param header
     * @param tableData
     */
    public void generateExcelData(List<String> header, List<List<Object>> tableData) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            int columnNumTotal = header.size();
            int rowNumTotal = tableData.size();
            // 设置表头(第一行)
            Row headerRow = sheet.createRow(0);
            for (int column = 0; column < columnNumTotal; column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(header.get(column));
            }
            // 设置数据
            for (int row = 0; row < rowNumTotal; row++) {
                Row currentRow = sheet.createRow(row + 1);
                // 当前行数据
                List<Object> rowData = tableData.get(row);
                for (int col = 0; col < rowData.size(); col++) {
                    Cell cell = currentRow.createCell(col);
                    XSSFRichTextString textString = new XSSFRichTextString(rowData.get(col).toString());
                    cell.setCellValue(textString);
                }
            }
            FileOutputStream outputStream = new FileOutputStream("output.xlsx");
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}