package com.univ.thirdutils.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 支持不限层级的复杂合并表头
 * 核心思想：将表头结构视为一棵或多棵多叉树（森林），然后通过递归遍历这些树结构来确定单元格的合并范围（行跨度和列跨度）并创建单元格。
 */
public class PoiComplicateHeadTest {

    /**
     * 支持多层级的复杂表头
     *
     * 实现的本质仍然是：从上到下、从左到右创建单元格而已
     */
    @Test
    public void complicatedHeader() throws IOException {
        // List<FieldConfig> headList = twoLevelHead();
        // List<FieldConfig> headList = threeLevelHead();
        List<FieldConfig> headList = fourLevelHead();
        // List<FieldConfig> headList = fiveLevelHead();

        // 1. 找出最大列的深度，用来合并单元格用，即列要占的行数
        int treeDepth = findMaxDepth(headList);

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("复杂表头");
            // 样式只创建一次
            XSSFCellStyle headerStyle = createHeaderStyle(workbook);
            // 创建表头
            int currentColIndex = 0;
            for (FieldConfig fieldConfig : headList) {
                currentColIndex = appendHead(sheet, 0, fieldConfig, treeDepth, currentColIndex, headerStyle);
            }

            // 同时写入表格数据，注释此行则只生成表头
            writeTableData(sheet, treeDepth, dataModelList(), headList);

            try (FileOutputStream fos = new FileOutputStream("data/excel/多级复杂表头.xlsx")) {
                workbook.write(fos);
            }
        }
    }


    /**
     * 从dataModel实例中获取指定属性的值
     * 注：这里得处理数据类型问题，写到单元格中的值(cell.setCellValue方法)必须指定数据类型，不能用Object
     *
     * @param dataModel 指定实例
     * @param name 指定属性
     * @return 指定实例对应的指定属性的值
     */
    private Object getPropertyValueFromObj(DataModel dataModel, String name) {
        try {
            Field field = DataModel.class.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(dataModel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return "unknown";
        }
    }

    /**
     * 按照顺序给所有叶子节点编号(列号)，处理整个森林
     * @param headList 整个表头(即整个森林)，即表头元数据
     * @return key：colIndex， value：对应的表头元数据(FieldConfig)
     *  重点：之所以value是FieldConfig，是因为要从尝试从数据中获取对应列名的值，FieldConfig是媒介
     */
    private Map<Integer, FieldConfig> toColIndexMap(List<FieldConfig> headList) {
        Map<Integer, FieldConfig> colIndexMap = new LinkedHashMap<>();
        int currentColIndex = 0;
        for (FieldConfig fieldConfig : headList) {
            currentColIndex = calculateColIndexMap(fieldConfig, currentColIndex, colIndexMap);
        }
        return colIndexMap;
    }

    /**
     * 按照顺序给所有叶子节点编号(列号)，处理一棵树
     *
     * @param fieldConfig 森林中的一棵树，即表头元数据
     * @param currentColIndex 当前列索引
     * @param colIndexMap 用来收集结果 key：colIndex， value：对应的表头元数据(FieldConfig)
     * @return 下一个要处理的叶子节点所在的列
     */
    private int calculateColIndexMap(FieldConfig fieldConfig, int currentColIndex, Map<Integer, FieldConfig> colIndexMap) {
        if (CollectionUtils.isEmpty(fieldConfig.children)) {
            colIndexMap.put(currentColIndex, fieldConfig);
            currentColIndex++;
            return currentColIndex;
        } else {
            for (FieldConfig config : fieldConfig.children) {
                currentColIndex = calculateColIndexMap(config, currentColIndex, colIndexMap);
            }
            return currentColIndex;
        }
    }

    /**
     * 将数据写入多级表格
     * 核心思路：表格数据适配表头结构，具体的：
     *  遍历表头，构建(叶子节点所在的列, 叶子节点)组成的map；
     *  遍历表格数据，遍历表头(此时是叶子节点)，找出其在对应dataModel中的值并写入
     *
     * @param sheet 当前sheet页，即创建了多级表头的sheet页
     * @param rowIndexStart 从这一行开始写入
     * @param dataModelList 表格数据
     * @param headList 表头数据
     */
    private void writeTableData(XSSFSheet sheet,
                                int rowIndexStart,
                                List<DataModel> dataModelList,
                                List<FieldConfig> headList) {
        Map<Integer, FieldConfig> colIndexMap = toColIndexMap(headList);

        // 逐行数据处理
        for (DataModel dataModel : dataModelList) {
            // 创建行
            XSSFRow row = sheet.createRow(rowIndexStart);
            // 按顺序尝试给对应单元格填充值
            colIndexMap.forEach((colIndex, fieldConfig) -> {
                // 创建列
                XSSFCell cell = row.createCell(colIndex);
                String cellValue = getPropertyValueFromObj(dataModel, fieldConfig.getName()).toString();
                cell.setCellValue(cellValue);
            });
            // 处理下一个数据
            rowIndexStart++;
        }
    }

    /**
     * 缓存样式
     * @param workbook 用来创建需要的样式
     */
    private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return headerStyle;
    }

    /**
     * 将当前节点添加到sheet页表头中
     * @param sheet 固定sheet页
     * @param rowIndex 当前行
     * @param fieldConfig 要添加到表头中的数据
     * @param maxColDepth 整个森林中树的最大的深度
     * @param currentColIndex 当前列
     * @param headerStyle 单元格要应用的样式
     * @return 下一个要处理的列。重要：利用这点就不需要用共享变量来保存当前处理到哪一列了
     */
    private int appendHead(XSSFSheet sheet,
                            int rowIndex,
                            FieldConfig fieldConfig,
                            int maxColDepth,
                            int currentColIndex,
                            XSSFCellStyle headerStyle) {
        // 重要：如果重复创建则会覆盖掉之前的行，包含数据和新式
        XSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
        if (CollectionUtils.isEmpty(fieldConfig.children)) {
            // 判断是否需要合并，CellRangeAddress必须得实际合并两个小单元格以上，否则异常
            if (rowIndex < maxColDepth - 1) { // 只有在不是最底层行时才需要向下合并
                CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, maxColDepth - 1, currentColIndex, currentColIndex);
                sheet.addMergedRegion(mergeCols);
            }
            XSSFCell cell = row.createCell(currentColIndex);
            cell.setCellValue(fieldConfig.getName());
            cell.setCellStyle(headerStyle); // 应用样式
            return currentColIndex + 1; // 返回下一个要处理的列
        } else {
            // 有子节点，先处理自己,宽度是所有子节点的宽度之和
            CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, rowIndex, currentColIndex, currentColIndex + width(fieldConfig) -1);
            sheet.addMergedRegion(mergeCols);
            XSSFCell cell = row.createCell(currentColIndex);
            cell.setCellValue(fieldConfig.getName());
            cell.setCellStyle(headerStyle); // 应用样式
            // 继续处理子节点
            for (FieldConfig child : fieldConfig.children) {
                currentColIndex = appendHead(sheet, rowIndex + 1, child, maxColDepth, currentColIndex, headerStyle);
            }
            return currentColIndex; // 返回所有子节点处理完后的最终索引
        }
    }

    /**
     * 森林中树的最大的深度
     * @param fieldConfigs 森林
     */
    private int findMaxDepth(List<FieldConfig> fieldConfigs) {
        int maxDepth = 1;
        for (FieldConfig fieldConfig : fieldConfigs) {
            maxDepth = Math.max(depth(fieldConfig), maxDepth);
        }
        return maxDepth;
    }

    /**
     * 获取指定树的深度,其实就是求多叉树的深度
     */
    private static int depth(FieldConfig fieldConfig) {
        int maxChildDepth = 0;
        if (CollectionUtils.isNotEmpty(fieldConfig.children)) {
            for (FieldConfig child : fieldConfig.children) {
                int childDepth = depth(child);
                maxChildDepth = Math.max(maxChildDepth, childDepth);
            }
        }
        return 1 + maxChildDepth;
    }

    /**
     * 此节点要占用的列的个数，其实就是叶子节点的个数
     */
    private static int width(FieldConfig fieldConfig) {
        if (CollectionUtils.isEmpty(fieldConfig.children)) {
            return 1;
        }
        int width = 0;
        for (FieldConfig child : fieldConfig.children) {
            width += width(child);
        }
        return width;
    }

    public static void main(String[] args) {
        FieldConfig f2 = new FieldConfig("f2", Arrays.asList(
                new FieldConfig("f2-1", null),
                new FieldConfig("f2-2", Arrays.asList(
                        new FieldConfig("f2-2-1", Arrays.asList(
                                new FieldConfig("f2-2-1-1", null))
                        ))),
                new FieldConfig("f2-3", null)
        ));
        // System.out.println(depth(f2));
        System.out.println(width(f2));

    }

    private List<FieldConfig> twoLevelHead() {
        // 构造表头数据
        FieldConfig f1 = new FieldConfig("f1", null);
        FieldConfig f2 = new FieldConfig("f2", Arrays.asList(
                new FieldConfig("f2-1", null),
                new FieldConfig("f2-2", null),
                new FieldConfig("f2-3", null)
        ));
        FieldConfig f3 = new FieldConfig("f3", null);
        return Arrays.asList(f1, f2, f3);
    }

    private List<DataModel> dataModelList() {
        return Arrays.asList(
                new DataModel("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "x", "xx", "xxx"),
                new DataModel("b", "bb", "bbb", "bbbb", "bbbbb", "bbbbbb", "y", "yy", "yyy"),
                new DataModel("c", "cc", "ccc", "cccc", "ccccc", "cccccc", "z", "zz", "zzz")
        );
    }
    private List<FieldConfig> threeLevelHead() {
        // 构造表头数据
        FieldConfig f1 = new FieldConfig("f1", null);
        FieldConfig f2 = new FieldConfig("f2", Arrays.asList(
                new FieldConfig("f2-1", null),
                new FieldConfig("f2-2", Arrays.asList(
                        new FieldConfig("f2-2-1", null),
                        new FieldConfig("f2-2-2", null))),
                new FieldConfig("f2-3", Arrays.asList(
                        new FieldConfig("f2-3-1", null),
                        new FieldConfig("f2-3-2", null))
                ),
                new FieldConfig("f2-3-3", null)));
        FieldConfig f3 = new FieldConfig("f3", null);
        return Arrays.asList(f1, f2, f3);
    }

    private List<FieldConfig> fourLevelHead() {
        // 构造表头数据
        FieldConfig f1 = new FieldConfig("f1", null);
        FieldConfig f2 = new FieldConfig("f2", Arrays.asList(
                new FieldConfig("f2_1", null),
                new FieldConfig("f2_2", Arrays.asList(
                        new FieldConfig("f2_2_1", null),
                        new FieldConfig("f2_2_2", null))),
                new FieldConfig("f2_3", Arrays.asList(
                        new FieldConfig("f2_3_1", null),
                        new FieldConfig("f2_3_2", Arrays.asList(
                                new FieldConfig("f2_3_2_1", null),
                                new FieldConfig("f2_3_2_2", null)))
                )),
                new FieldConfig("f2_3_3", null)));
        FieldConfig f3 = new FieldConfig("f3", null);
        return Arrays.asList(f1, f2, f3);
    }

    private List<FieldConfig> fiveLevelHead() {
        // 构造表头数据
        FieldConfig f1 = new FieldConfig("f1", null);
        FieldConfig f2 = new FieldConfig("f2", Arrays.asList(
                new FieldConfig("f2-1", null),
                new FieldConfig("f2-2", Arrays.asList(
                        new FieldConfig("f2-2-1", null),
                        new FieldConfig("f2-2-2", null))),
                new FieldConfig("f2-3", Arrays.asList(
                        new FieldConfig("f2-3-1", null),
                        new FieldConfig("f2-3-2", Arrays.asList(
                                new FieldConfig("f2-3-2-1", null),
                                new FieldConfig("f2-3-2-2", Arrays.asList(
                                        new FieldConfig("f2-2-1", null),
                                        new FieldConfig("f2-2-2", null)
                                ))))
                )),
                new FieldConfig("f2-3-3", null)));
        FieldConfig f3 = new FieldConfig("f3", null);
        return Arrays.asList(f1, f2, f3);
    }

    @Data
    @AllArgsConstructor
    static class FieldConfig {
        private String name;
        private List<FieldConfig> children;
    }

    @Data
    @AllArgsConstructor
    static class DataModel {
        // 简单起见，这里的名字就和表格的列名保持一致，也可以在FieldConfig中指定dataKey；
        private String f1;
        private String f2_1;
        private String f2_2_1;
        private String f2_2_2;
        private String f2_3_1;
        private String f2_3_2_1;
        private String f2_3_2_2;
        private String f2_3_3;
        private String f3;
    }
}
