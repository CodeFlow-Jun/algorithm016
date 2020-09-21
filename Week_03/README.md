# 学习笔记

---------------------

### *** 一、递归 ***
* 树的面试题解法一般都用递归；
* 递归，向下深一层，向上前一层；
* 注意：切忌人肉递归，要找最近重复子问题，利用数学归纳法求解；
* 递归代码模版：
```java
  public void helper(int level, int param) { 
    // 递归终止条件
    if (level > MAX_LEVEL) { 
      // 该层结果 
      return; 
    }
    // 处理当前层逻辑 
    process(level, param); 
    // 进入下一层递归
    helper( level: level + 1, newParam); 
  }
```
#### **递归-实战题目**
 题号   |   名称  |  难度  |   分类  |   题解    
 ----- | ------ | ------ | ------- | --------- 
 [70](https://leetcode-cn.com/problems/climbing-stairs/ "爬楼梯") | [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/ "爬楼梯") | 简单 | 动态规划 | 伪递归、动态规划
 [22](https://leetcode-cn.com/problems/generate-parentheses/ "括号生成") | [括号生成](https://leetcode-cn.com/problems/generate-parentheses/ "括号生成") | 中等 | 字符串、回溯算法 | 递归
