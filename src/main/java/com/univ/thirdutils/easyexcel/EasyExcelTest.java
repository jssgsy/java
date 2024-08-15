package com.univ.thirdutils.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author univ
 * date 2024/5/7
 */
@Slf4j
public class EasyExcelTest {

    // 逐行处理所有sheet页数据
    @Test
    public void readAll() {
        String excelFilePath = "data/excel/2007.xlsx";
        // 1. 获取ExcelReaderBuilder对象
        ExcelReaderBuilder readerBuilder = EasyExcel.read(excelFilePath, EasyPO.class, new ReadListener() {
            @Override
            public void invoke(Object data, AnalysisContext context) {
                log.info("数据行被读取到了：{}", data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                try {
                    Thread.sleep(1000L * 3);
                } catch (InterruptedException e) {

                }
                log.info("所有数据行被读取到了。");
            }
        });
        // 2. 设置各种属性
        readerBuilder.head(EasyPO.class);
        // 3. 调用doReadAll方法进行实际读取
        // 经验证，这里会阻塞读
        readerBuilder.doReadAll();
        log.info("###end###");
    }

    // 收集所有sheet页数据交由应用程序处理
    @Test
    public void readAllSync() {
        String excelFilePath = "data/excel/2007.xlsx";
        // 1. 获取ExcelReaderBuilder对象
        ExcelReaderBuilder readerBuilder = EasyExcel.read(excelFilePath);
        // 2. 设置各种属性
        readerBuilder.head(EasyPO.class);
        // 3. 调用doReadAllSync方法进行实际读取
        List<EasyPO> dataLists = readerBuilder.doReadAllSync();
        dataLists.forEach(System.out::println);
    }

    @Test
    public void readFromSpecificSheet() {
        String excelFilePath = "data/excel/2007.xlsx";
        // 1. 获取ExcelReaderBuilder对象
        ExcelReaderBuilder readerBuilder = EasyExcel.read(excelFilePath);
        // 2. 设置各种属性
        readerBuilder.head(EasyPO.class);
        // 此时必须使用ExcelReaderSheetBuilder对象
        ExcelReaderSheetBuilder readerSheetBuilder = readerBuilder.sheet(0);
        List<EasyPO> dataLists = readerSheetBuilder.doReadSync();
        dataLists.forEach(System.out::println);
    }

    @Test
    public void writeBasic() {
        String excelFilePath = "data/excel/2007.xlsx";
        // 1.1. 获取ExcelWriterBuilder对象
        ExcelWriterBuilder writerBuilder = EasyExcel.write(excelFilePath, EasyPO.class);
        // 2. 写入之前的配置
//        writerBuilder.excludeColumnFieldNames(Collections.emptyList());
//        writerBuilder.includeColumnFieldNames(Collections.emptyList());
        // 1.2. 获取ExcelWriterSheetBuilder对象
        // 与读不同，读既可以读所有sheet又可以读指定sheet，但定一定是写到指定sheet中
        // sheet()表示写入到第一个sheet(下标为0)页中
        ExcelWriterSheetBuilder sheetBuilder = writerBuilder.sheet();

        // 3. 实际写入
        // 是由ExcelWriterSheetBuilder对象写而不是writerBuilder
        sheetBuilder.doWrite(data());
    }

    /**
     * 同时写多个sheet
     * 这里要注意WriteSheet实例的获取
     */
    @Test
    public void writeMultiSheet() {
        String excelFilePath = "data/excel/2007.xlsx";
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(excelFilePath, EasyPO.class);

        // 注意WriteSheet的实例方法，必须使用EasyExcel.writerSheet，此时创建的Sheet和ExcelWriter没关联
        // 所以可被任意ExcelWriter对象写入数据
        WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "第一个sheet").build();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "第二个sheet").build();
        // 下面获取实例方法是不ok，因为此时已经和excelWriterBuilder关联了
        // WriteSheet writeSheet = excelWriterBuilder.sheet().build();
        // 每调用一次build方法会新建一个ExcelWriter对象
        try (ExcelWriter excelWriter = excelWriterBuilder.build()) {
            excelWriter.write(data(), writeSheet1);
            excelWriter.write(data(), writeSheet2);
        }
    }


    private List<EasyPO> data() {
        return Arrays.asList(
                new EasyPO("aaax", 10, new Date()),
                new EasyPO("bbby", 20, new Date()),
                new EasyPO("cccz", 30, new Date())
        );
    }


}
