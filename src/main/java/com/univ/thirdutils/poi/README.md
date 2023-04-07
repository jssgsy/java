# [官网](https://poi.apache.org/index.html)

# 引入依赖
```xml
<!--处理excel文件-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.17</version>
</dependency>
<!--这个也不能少-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.17</version>
</dependency>

```

# HSSF与XSSF
> HSSF is the POI Project's pure Java implementation of the Excel '97(-2007) file format. 
> XSSF is the POI Project's pure Java implementation of the Excel 2007 OOXML (.xlsx) file format.

即HSSF是用来处理后缀为xls的excel文件的；XSSF是用来处理后缀为xlsx的excel文件的；

* 不同版本的excel使用的api不同，在高版本的poi中可以直接使用统一的WorkBook，Sheet等来统一处理
* Workbook是HSSFWorkbook、XSSFWorkbook等的父类