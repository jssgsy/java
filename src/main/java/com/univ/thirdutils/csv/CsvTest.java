package com.univ.thirdutils.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author univ
 * @date 2019/7/23 3:17 PM
 * @description 使用commons-csv来读取和生成CSV文件
 */
public class CsvTest {

    private String[] headers = new String[] {"id", "name", "age", "married"};

    private String csvFileName = "stu.csv";

    @Test
    public void writeCSV() throws IOException {
        List<CSVData> csvDataList = Arrays.asList(
                new CSVData(1, "aaa", 10, true),
                new CSVData(2, "bbb", 20, false),
                new CSVData(3, "ccc", 30, true),
                new CSVData(4, "ddd", 40, false)
        );

        FileWriter out = new FileWriter(csvFileName);
        CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers));
        for (CSVData csvData : csvDataList) {
            csvPrinter.printRecord(csvData.getId(), csvData.getName(), csvData.getAge(), csvData.getMarried());
        }
        csvPrinter.close();
    }

    @Test
    public void readCSV() throws IOException {
        Reader in = new FileReader(csvFileName);
        CSVFormat csvFormat = CSVFormat.DEFAULT // 接下来有很多选项来控制解析csv文件的行为
                .withHeader(headers)
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase() // 忽略头的大小写，这样record.get("id")与record.get("Id")、record.get("ID")都一样的效果
                .withIgnoreEmptyLines() // 忽略空行， 默认就是true
                ;
        // 注，CSVParser实现了Iterable接口，所以这里直接使用了Iterable<CSVRecord>，
        // 但是CSVParser本身也有很多有用的方法，如获取请求头
        Iterable<CSVRecord> records = csvFormat.parse(in);
        for (CSVRecord record : records) {
            System.out.println(record.get("id") + "  " + record.get("name") + "  " + record.get("age") + "  " + record.get("married"));
        }

    }

    /**
     * 将二维列表转换成以列为key，对应所有列值为list的map
     * @throws IOException
     */
    @Test
    public void readCSV_v2() throws IOException {
        Reader in = new FileReader(csvFileName);
        CSVFormat csvFormat = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withIgnoreEmptyLines()
            .withAllowMissingColumnNames()
            ;
        // 这里没以父类作为类型，因为要使用此类本身的方法
        CSVParser parse = csvFormat.parse(in);
        // 获取头
        Map<String, Integer> headerMap = parse.getHeaderMap();
        Map<String, List<String>> dataMap = new LinkedHashMap<>();
        headerMap.forEach((header, v) -> {
            if (StringUtils.isNotEmpty(header)) { // 兼容空列，空列不处理
                dataMap.put(header, new LinkedList<>());
            }
        });
        for (CSVRecord csvRecord : parse) {
            headerMap.forEach((headerName, v) -> {
                if (StringUtils.isNotEmpty(headerName)) {// 兼容空列，空列不处理
                    dataMap.get(headerName).add(csvRecord.get(headerName));
                }
            });
        }
        System.out.println(dataMap);
    }

}

/**
 * 表示csv文件中的一行，往csv文件中定数据时方便
 */
@Data
class CSVData {
    private Integer id;

    private String name;

    private Integer age;

    private Boolean married;

    public CSVData(Integer id, String name, Integer age, Boolean married) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.married = married;
    }
}
