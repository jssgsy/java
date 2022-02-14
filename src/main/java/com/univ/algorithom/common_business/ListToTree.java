package com.univ.algorithom.common_business;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 将具有层级关系的list转成树形结构
 * @author univ
 * 2022/2/14 11:43 上午
 */
public class ListToTree {

    List<Pojo> pojos ;
    {
        Pojo p1 = new Pojo(1L, "1", 0L, null);
        Pojo p2 = new Pojo(2L, "2", 1L, null);
        Pojo p3 = new Pojo(3L, "3", 0L, null);
        Pojo p4 = new Pojo(4L, "4", 1L, null);
        Pojo p5 = new Pojo(5L, "5", 4L, null);
        Pojo p6 = new Pojo(6L, "6", 4L, null);
        Pojo p7 = new Pojo(7L, "7", 3L, null);
        Pojo p8 = new Pojo(8L, "8", 7L, null);
        Pojo p9 = new Pojo(9L, "9", 3L, null);
        Pojo p10 = new Pojo(10L, "10", 1L, null);
        Pojo p11 = new Pojo(11L, "11", 0L, null);
        Pojo p12 = new Pojo(12L, "12", 10L, null);
        pojos = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
    }

    /**
     * 双重循环
     * @throws InterruptedException
     */
    @Test
    public void twoForLoop() {
        fillChildren(pojos);
        // 只取顶级
        List<Pojo> resultList = pojos.stream().filter(t -> null == t.getPid() || t.getPid().equals(0L)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(resultList));
    }
    private void fillChildren(List<Pojo> enumExtendList) {
        for (Pojo commonEnumExtend : enumExtendList) {
            List<Pojo> children = enumExtendList.stream().filter(t -> t.getPid().equals(commonEnumExtend.getId())).collect(Collectors.toList());
            commonEnumExtend.setChildren(children);
        }
    }

    /**
     * 一重循环：找父节点，并将当前节点添加到找到的父节点的children字段中；
     */
    @Test
    public void oneLoop() {
        Map<Long, Pojo> map = pojos.stream().collect(Collectors.toMap(Pojo::getId, Function.identity()));
        pojos.forEach(pojo -> {
            if (null != pojo.getPid() && 0L != pojo.getPid()) {
                Pojo parentPojo = map.get(pojo.getPid());
                if (CollectionUtils.isEmpty(parentPojo.getChildren())) {
                    parentPojo.setChildren(new LinkedList<>());
                }
                parentPojo.getChildren().add(pojo);
            }
        });
        // 结果只取顶级
        List<Pojo> resultList = pojos.stream().filter(t -> null == t.getPid() || t.getPid().equals(0L)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(resultList));
    }

}

/**
 * 源数据
 */
@Data
@AllArgsConstructor
class Pojo {
    private Long id;
    private String name;
    private Long pid;

    /**
     * 此字段为应用层级添加，db中一般没有
     * 之所以需要children是因为一般前端树形组件需要children字段来表示子级进而渲染成树
     */
    private List<Pojo> children;
}
