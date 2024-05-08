package com.univ.thirdutils.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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

    // DateStringConverter默认是使用yyyy-MM-dd HH:mm:ss
    @ExcelProperty(value = "生日", order = 2)
    // 这里用@DateTimeFormat来指定pattern为yyyy-MM-dd
    // 也可以作用在LocalDate、LocalDateTime等类型上
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date birthday;
}
