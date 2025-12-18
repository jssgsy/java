package com.univ.thirdutils.poi;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Copyright: Unicom (Zhejiang) Industrial Internet Co., Ltd. 2025 <br/>
 * @Desc: 是PoiComplicateHeadTest.java的进化版，这里的树是异构树，即节点有两种类型
 * @ProjectName: java <br/>
 * @Date: 2025-12-18 11:05 <br/>
 * @Author: univ
 */
public class PoiHeterogeneousTreeHeadTest {

    @Test
    public void complicatedHeader() throws IOException {
        List<Node> headList = headList();

        // 1. 找出最大列的深度，用来合并单元格用，即列要占的行数
        int treeDepth = findMaxTreeDepth(headList);
        System.out.println(treeDepth);
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("复杂表头");
            // 样式只创建一次
            XSSFCellStyle headerStyle = createHeaderStyle(workbook);
            // 创建表头
            int currentColIndex = 0;
            for (Node node : headList) {
                currentColIndex = appendNodeHead(sheet, 0, node, treeDepth, currentColIndex, headerStyle);
            }

            try (FileOutputStream fos = new FileOutputStream("data/excel/异构树构建的多级复杂表头.xlsx")) {
                workbook.write(fos);
            }
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

    private int appendNodeHead(XSSFSheet sheet,
                               int rowIndex,
                               Node node,
                               int maxColDepth,
                               int currentColIndex,
                               XSSFCellStyle headerStyle) {
        if (CollectionUtils.isNotEmpty(node.getFieldList())) {
            // 有直接关联字段，则说明没有子节点，这是业务数据决定的
            // 1. 先处理自己
            // 只关联一个字段就不用合并了
            addNodeSelfToSheetHead(sheet, headerStyle, node, rowIndex, currentColIndex, node.getFieldList().size() > 1);
            // 2. 再处理字段
            for (Field field : node.getFieldList()) {
                currentColIndex = appendFieldHead(sheet, rowIndex + 1, field, maxColDepth, currentColIndex, headerStyle);
            }
            return currentColIndex; // 返回下一个要处理的列
        }
        // 到这里说明有子节点
        if (CollectionUtils.isEmpty(node.getChildren())) {
            // 实际业务上这是不可能的，没有关联字段则必然有子节点；
            System.out.println("不应该执行的代码被执行了");
            return currentColIndex;
        }
        // 1. 先处理自己
        addNodeSelfToSheetHead(sheet, headerStyle, node, rowIndex, currentColIndex, node.getChildren().size() > 1);
        // 2. 处理子节点
        for (Node n : node.getChildren()) {
            currentColIndex = appendNodeHead(sheet, rowIndex + 1, n, maxColDepth, currentColIndex, headerStyle);
        }
        return currentColIndex; // 返回下一个要处理的列
    }

    /** 仅把自己(不处理任何子节点或子字段)加入到表头中
     * @param sheet 要回到哪个sheet页中
     * @param headerStyle 要应用的样式
     * @param node 要添加的目标节点
     * @param rowIndex 添加到哪一行
     * @param currentColIndex 添加到哪一列
     * @param needMergeCell 是否要合并单元格
     */
    private void addNodeSelfToSheetHead(XSSFSheet sheet,
                                        XSSFCellStyle headerStyle,
                                        Node node,
                                        int rowIndex,
                                        int currentColIndex,
                                        boolean needMergeCell) {
        // 重要：如果重复创建则会覆盖掉之前的行，包含数据和新式
        XSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
        if (needMergeCell) {
            // 此时才需要合并单元格，自己只占一行
            CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, rowIndex, currentColIndex, currentColIndex + nodeWidth(node) -1);
            sheet.addMergedRegion(mergeCols);
        }
        XSSFCell cell = row.createCell(currentColIndex);
        cell.setCellValue(node.getName());
        cell.setCellStyle(headerStyle); // 应用样式
    }

    private int appendFieldHead(XSSFSheet sheet,
                                int rowIndex,
                                Field field,
                                int maxColDepth,
                                int currentColIndex,
                                XSSFCellStyle headerStyle) {
        // 重要：如果重复创建则会覆盖掉之前的行，包含数据和新式
        XSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
        if (CollectionUtils.isEmpty(field.getChildren())) {
            // 判断是否需要合并，CellRangeAddress必须得实际合并两个小单元格以上，否则异常
            if (rowIndex < maxColDepth - 1 ) { // 只有在不是最底层行时才需要向下合并
                CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, maxColDepth - 1, currentColIndex, currentColIndex);
                sheet.addMergedRegion(mergeCols);
            }
            XSSFCell cell = row.createCell(currentColIndex);
            cell.setCellValue(field.getName());
            cell.setCellStyle(headerStyle); // 应用样式
            return currentColIndex + 1; // 返回下一个要处理的列
        } else {
            // 有子节点，先处理自己,宽度是所有子节点的宽度之和
            CellRangeAddress mergeCols = new CellRangeAddress(rowIndex, rowIndex, currentColIndex, currentColIndex + fieldWidth(field) -1);
            sheet.addMergedRegion(mergeCols);
            XSSFCell cell = row.createCell(currentColIndex);
            cell.setCellValue(field.getName());
            cell.setCellStyle(headerStyle); // 应用样式
            // 继续处理子节点
            for (Field child : field.getChildren()) {
                currentColIndex = appendFieldHead(sheet, rowIndex + 1, child, maxColDepth, currentColIndex, headerStyle);
            }
            return currentColIndex; // 返回所有子节点处理完后的最终索引
        }
    }

    /**
     * 森林中树的最大的深度
     * @param nodeList 森林
     */
    private int findMaxTreeDepth(List<Node> nodeList) {
        int maxDepth = 1;
        for (Node node : nodeList) {
            maxDepth = Math.max(treeDepth(node), maxDepth);
        }
        return maxDepth;
    }

    /**
     * 异构树的深度
     *  核心：深度 = 节点深度 + max(字段深度)
     * @param node 树
     */
    private static int treeDepth(Node node) {
        int nodeDepth = nodeOnlyDepth(node);
        int maxFieldDepth = maxFieldDepth(node.getFieldList());
        return 1 + nodeDepth + maxFieldDepth;
    }


    private static int maxFieldDepth(List<Field> fieldList) {
        if (CollectionUtils.isEmpty(fieldList)) {
            return 0;
        }
        int maxDepth = 1;
        for (Field field : fieldList) {
            maxDepth = Math.max(fieldOnlyDepth(field), maxDepth);
        }
        return maxDepth;
    }

    /**
     * 节点的深度
     * @param node 只求节点部分，如果节点下有字段则忽略之
     */
    private static int nodeOnlyDepth(Node node) {
        int maxChildDepth = 0;
        if (CollectionUtils.isNotEmpty(node.getChildren())) {
            for (Node child : node.getChildren()) {
                int childDepth = nodeOnlyDepth(child);
                maxChildDepth = Math.max(maxChildDepth, childDepth);
            }
        }
        return 1 + maxChildDepth;
    }

    /**
     * 仍然是节点下所有叶子节点的个数，因为本质是要节点要占用的列宽
     * @param node
     * @return
     */
    private static int nodeWidth(Node node) {
        if (CollectionUtils.isEmpty(node.getFieldList())) {
            // 此时看子节点
            if (CollectionUtils.isEmpty(node.getChildren())) {
                // 没有关联的字段，没有子节点，实际业务中不会出现
                return 0;
            }
            // 有子节点,等于所有子节点的宽度和
            int width = 0;
            for (Node n : node.getChildren()) {
                width += nodeWidth(n);
            }
            return width;
        }
        // 说明是节点下直接关联了字段
        int width = 0;
        for (Field field : node.getFieldList()) {
            width += fieldWidth(field);
        }
        return width;
    }

    /**
     * 字段的深度
     */
    private static int fieldOnlyDepth(Field field) {
        int maxChildDepth = 0;
        if (CollectionUtils.isNotEmpty(field.getChildren())) {
            for (Field child : field.getChildren()) {
                int childDepth = fieldOnlyDepth(child);
                maxChildDepth = Math.max(maxChildDepth, childDepth);
            }
        }
        return 1 + maxChildDepth;
    }

    /**
     * 字段的宽度，其实就是所有叶子节点的个数
     */
    private static int fieldWidth(Field field) {
        if (CollectionUtils.isEmpty(field.getChildren())) {
            return 1;
        }
        int width = 0;
        for (Field child : field.getChildren()) {
            width += fieldWidth(child);
        }
        return width;
    }

    public static List<Node> headList() {
        return Arrays.asList(
                new Node("n1", 1, 0, null, Arrays.asList(
                        new Field("n1-f1", 1, 0, null),
                        new Field("n1-f2", 2, 0, null)
                )), // 节点 --> 字段
                new Node("n2", 2, 0, null, Arrays.asList(
                        new Field("n2-f1", 21, 0, Arrays.asList(
                                new Field("n2-f1-f1", 221, 21, null),
                                new Field("n2-f1-f2", 222, 21, null)
                        )),
                        new Field("n2-f2", 22, 0, null))
                ),  // 节点 --> 字段  --> 子字段
                new Node("n3", 3, 0, Arrays.asList(
                        new Node("n3-n1", 31, 3, null, Arrays.asList(
                                new Field("n3-n1-f1", 311, 0, null),
                                new Field("n3-n1-f2", 312, 0, null)
                        )),
                        new Node("n3-n2", 31, 3, null, Arrays.asList(
                                new Field("n3-n2-f1", 311, 0, null),
                                new Field("n3-n2-f2", 312, 0, null)
                        )
                        )
                ), null),  // 节点 --> 子节点  --> 子字段
                new Node("n4", 4, 0, Arrays.asList(
                        new Node("n4-n1", 31, 3, null, Arrays.asList(
                                new Field("n4-n1-f1", 411, 0, Arrays.asList(
                                        new Field("n4-n1-f1-f1", 41111, 411, null),
                                        new Field("n4-n1-f1-f2", 41112, 411, null)
                                )),
                                new Field("n4-n1-f2", 412, 0, null)
                        )),
                        new Node("n4-n2", 31, 3, null, Arrays.asList(
                                new Field("n4-n2-f1", 311, 0, null),
                                new Field("n4-n2-f2", 312, 0, null)
                        )
                        )
                ), null)   // // 节点 --> 子节点  --> 字段 --> 子字段
        );
    }


}

/**
 * 表示一棵树
 * 重点；树的节点有两种类型，一种是Node，一种是Field，且两者均是树结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class Node {
    private String name;
    private Integer id;
    private Integer parentId;
    private List<Node> children;
    private List<Field> fieldList;
}


@AllArgsConstructor
@NoArgsConstructor
@Data
class Field {
    private String name;
    private Integer id;
    private Integer parentId;
    private List<Field> children;
}
