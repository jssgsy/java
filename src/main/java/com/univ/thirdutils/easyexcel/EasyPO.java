package com.univ.thirdutils.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.converters.date.DateStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author univ
 * date 2024/5/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyPO {

    @ExcelProperty(value = "姓名", order = 0)
    private String name;

    @ExcelProperty(value = "年龄", order = 1)
    private Integer age;

    // DateStringConverter默认主是使用yyyy-MM-dd HH:mm:ss，所以可省略
    // 不指定converter也不使用@DateTimeFormat，easyexcel默认会按照yyyy-MM-dd HH:mm:ss格式写
    @ExcelProperty(value = "生日", order = 2, converter = DateStringConverter.class)
    // 这里用@DateTimeFormat来指定pattern为yyyy-MM-dd
    // 使用了@DateTimeFormat，就不必再指定converter了，即使指定也会优先使用@DateTimeFormat中的pattern
    // 也可以作用在LocalDate、LocalDateTime等类型上
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date birthday;
}
