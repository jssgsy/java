package com.univ.thirdutils.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    // 基本思路：从上到下创建行，然后在一行中从左到右创建单元格；
    // 重点：行其实和sheet一样仍然只是个容器，只有创建出了单元格才算是真正有了表格
    @Test
    public void writeBasicOps() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row1 = sheet.createRow(0);  //  row和cell均从0开始
        Cell c1 = row1.createCell(0);
        c1.setCellValue("aaa"); // 第一行一般当作表头；

        Cell c2 = row1.createCell(1);
        c2.setCellValue("bbb");

        Row row2 = sheet.createRow(1);
        Cell c21 = row2.createCell(0);
        c21.setCellValue("ccc");

        Cell c22 = row2.createCell(1);
        c22.setCellValue("ddd");

        FileOutputStream outputStream = new FileOutputStream("data/excel/output.xlsx");
        workbook.write(outputStream);
        // 关闭workbook，释放资源
        workbook.close();
    }

    @Test
    public void writeToExcelV1() {
        List<Person> personList = Arrays.asList(
                new Person("aaa", "wuhan", 10),
                new Person("bbb", "hangzhou", 20),
                new Person("ccc", "qinghai",30)
        );
        List<String> headerList = Arrays.asList("名字", "城市", "年龄");

        // 转成List<List<Object>>
        List<List<Object>> tableData = new ArrayList<>();
        // important：赋值顺序需要和表头保持一致
        personList.forEach(person -> {
            List<Object> list = new ArrayList<>();
            list.add(person.getName());
            list.add(person.getCity());
            list.add(person.getAge());
            tableData.add(list);
        });
        generateExcelDataV1(headerList, tableData);
    }

    /**
     * 重点：
     *  1. 表格数据必须使用List<List<Object>>，而不能是List<Person>等形式，因为此时无法逐个单元格迭代；
     *      即必须将List<Person>等形式转成List<List<Object>>的形式；
     *  2. header中表头的值必须和List<Object>中值的顺序是一样的；
     */
    public void generateExcelDataV1(List<String> headerList, List<List<Object>> tableData) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            // 设置表头(第一行)
            Row headerRow = sheet.createRow(0);
            for (int column = 0; column < headerList.size(); column++) {
                Cell cell = headerRow.createCell(column);
                cell.setCellValue(headerList.get(column));
            }
            // 是一行一行赋值，因此外围循环的是tableData
            for (int row = 0; row < tableData.size(); row++) {
                Row currentRow = sheet.createRow(row + 1);
                // 当前行数据
                List<Object> rowData = tableData.get(row);
                // rowData.size()就是headerList.size()
                for (int col = 0; col < rowData.size(); col++) {
                    Cell cell = currentRow.createCell(col);
                    XSSFRichTextString textString = new XSSFRichTextString(rowData.get(col).toString());
                    cell.setCellValue(textString);
                }
            }
            FileOutputStream outputStream = new FileOutputStream("data/excel/output.xlsx");
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 自定义注解来处理Excel
    @Test
    public void writeToExcelV2() throws IOException, IllegalAccessException {
        List<Person> personList = Arrays.asList(
                new Person("zhangsan", "city1", 1),
                new Person("lis", "city2", 2),
                new Person("univ",  "city3", 10),
                new Person("who",  "city4", 20),
                new Person("wangwu",  "city4", 30)
        );
        generateExcelData2(personList);
    }

    private void generateExcelData2(List<?> tableData) {
        // 处理是空列表问题
        if (CollectionUtils.isEmpty(tableData)) {
            return;
        }
        List<String> headers = listHeaders(tableData.get(0).getClass());
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row rowZero = sheet.createRow(0);
            // 先写入表头
            for (int i = 0; i < headers.size(); i++) {
                // 这里不能使用Stream的forEach，因为要通过i来创建单元格(当然是可以的，只是麻烦些)
                Cell cell = rowZero.createCell(i);
                // 必须要能获取到值，因此headers定义成了List而不能是Collection
                cell.setCellValue(headers.get(i));
            }

            // 这里应该放到for外面，因为field只是静态信息
            Field[] declaredFields = tableData.get(0).getClass().getDeclaredFields();
            // 过滤有被@Excel注解的字段，这样就和headers有相同个数元素了
            List<Field> excelFields = Arrays.stream(declaredFields).filter(t -> t.isAnnotationPresent(Excel.class)).collect(Collectors.toList());
            Map<Integer, Field> fieldMap = excelFields.stream().collect(Collectors.toMap(field -> field.getAnnotation(Excel.class).order(), Function.identity()));
            // 总共有这么多行数据要写，先遍历数据再遍历头
            for (int row = 0; row < tableData.size(); row++) {
                // 创建一行
                Row currentRow = sheet.createRow(row + 1);
                Object rowData = tableData.get(row);
                for (int col = 0; col < headers.size(); col++) {
                    Cell cell = currentRow.createCell(col);
                    // 此时col的值就是@Excel注解中value值
//                    int order = col;
                    // 找到对哪个字段取值：@Excel的order值是col的字段
                    Field field = fieldMap.getOrDefault(col, null);
                    if (null != field) {
                        field.setAccessible(true);
                        cell.setCellValue(field.get(rowData).toString());
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream("data/excel/output.xlsx");
            workbook.write(outputStream);
        } catch (Exception exception) {
            // 异常处理
        }

    }

    /**
     * 从有被@Excel注解修饰字段中收集表头，且表头已经排好序
     * @param clz 包含@Excel修饰字段的类
     */
    private List<String> listHeaders(Class<?> clz) {
        Map<Integer, String> map = new TreeMap<>();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Excel.class)) {
                Excel annotation = field.getAnnotation(Excel.class);
                map.put(annotation.order(), annotation.header());
            }
        }
        // 这里不能直接使用Collection，因为Collection没法通过下标方式取得逐个元素的值
        return new ArrayList<>(map.values());
    }

}

@Data
@AllArgsConstructor
class Person {
    @Excel(order = 0, header = "姓名")
    private String name;

    @Excel(order = 1, header = "城市")
    private String city;

     @Excel(order = 2, header = "god")
    private Integer age;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Excel {

    /**
     * 表头的顺序，从0开始，需连续递增
     */
    int order() default 0;

    /**
     * 表头名称
     */
    String header() default "请显式赋值";
}