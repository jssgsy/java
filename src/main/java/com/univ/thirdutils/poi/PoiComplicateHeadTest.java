package com.univ.thirdutils.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 支持不限层级的复杂合并表头
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
            // 创建表头
            int currentColIndex = 0;
            for (FieldConfig fieldConfig : headList) {
                currentColIndex = appendHead(sheet, 0, fieldConfig, treeDepth, currentColIndex);
            }
            try (FileOutputStream fos = new FileOutputStream("data/excel/多级复杂表头.xlsx")) {
                workbook.write(fos);
            }
        }
    }


    /**
     * 将当前节点添加到sheet页表头中
     * @param sheet 固定sheet页
     * @param rowIndex 当前行
     * @param fieldConfig 要添加到表头中的数据
     * @param maxColDepth 整个森林中树的最大的深度
     * @param currentColIndex 当前列
     * @return 下一个要处理的列。重要：利用这点就不需要用共享变量来保存当前处理到哪一列了
     */
    private int appendHead(XSSFSheet sheet,
                            int rowIndex,
                            FieldConfig fieldConfig,
                            int maxColDepth,
                            int currentColIndex) {
        // 重要：如果重复创建则会覆盖掉之前的行，包含数据和新式
        XSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
        if (CollectionUtils.isEmpty(fieldConfig.children)) {
            // 判断是否需要合并，CellRangeAddress必须得实际合并两个小单元格以上，否则异常
            if (rowIndex == maxColDepth -1) {
                XSSFCell cell = row.createCell(currentColIndex);
                cell.setCellValue(fieldConfig.getName());
            } else {
                CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, maxColDepth -1 , currentColIndex, currentColIndex);
                sheet.addMergedRegion(mergeCols);
                XSSFCell cell = row.createCell(currentColIndex);
                cell.setCellValue(fieldConfig.getName());
            }
            return currentColIndex + 1; // 返回下一个要处理的列
        } else {
            // 有子节点，先处理自己,宽度是所有子节点的宽度之和
            CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, rowIndex, currentColIndex, currentColIndex + width(fieldConfig) -1);
            sheet.addMergedRegion(mergeCols);
            XSSFCell cell = row.createCell(currentColIndex);
            cell.setCellValue(fieldConfig.getName());
            // 继续处理子节点
            for (FieldConfig child : fieldConfig.children) {
                currentColIndex = appendHead(sheet, rowIndex + 1, child, maxColDepth, currentColIndex);
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
                new FieldConfig("f2-1", null),
                new FieldConfig("f2-2", Arrays.asList(
                        new FieldConfig("f2-2-1", null),
                        new FieldConfig("f2-2-2", null))),
                new FieldConfig("f2-3", Arrays.asList(
                        new FieldConfig("f2-3-1", null),
                        new FieldConfig("f2-3-2", Arrays.asList(
                                new FieldConfig("f2-3-2-1", null),
                                new FieldConfig("f2-3-2-2", null)))
                )),
                new FieldConfig("f2-3-3", null)));
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
}
