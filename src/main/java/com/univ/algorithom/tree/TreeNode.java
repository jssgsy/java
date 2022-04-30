package com.univ.algorithom.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树节点的表现形式
 * @author univ
 * 2022/4/24 11:06 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<Value extends Comparable<Value>> {
    Value value;
    TreeNode<Value> left;
    TreeNode<Value> right;
}
