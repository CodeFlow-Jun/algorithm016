# 学习笔记

---------------------

## ***一、递归***
* 树的面试题解法一般都用递归；
* 递归，向下深一层，向上前一层；
* 注意：切忌人肉递归，要找最近重复子问题，利用数学归纳法求解；
* **递归代码模版：**
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
* **DFS算法**其实就是**回溯算法**；
* **BFS算法：** <br>
    > 1. BFS算法的核心思想，就是把一些问题抽象成图，从一个点开始，向四周扩散。<br>
    > 2. 一般BFS算法都是采用 **队列** 这种数据结构， 每次将一个节点周围的所有节点入队。<br>
* **BFS 与 DFS 最主要的区别**：BFS 找到的路径⼀定是最短的，但代价就是空间复杂度⽐ DFS ⼤很多。
* **BFS算法 代码模版：**
        **应用场景：在给出的 “图” 中，找到从 起点start 到 终点target 的最近距离(最短路径)。**<br>
        **经典题目：** “111. 二叉树的最小高度”、“752. 打开密码锁的最小步数” 等；
```java
  // 计算从起点 start 到终点 target 的最近距离
  int BFS(Node start, Node target) {
      Queue<Node> q; // 核心数据结构
      Set<Node> visited; // 避免走回头路
      
      q.offer(start); // 将起点加入队列
      visited.add(start);
      int step = 0; // 记录扩散的步数
      
      while(q not empty) {
          int sz = q.size();
          /* 将当前队列中的所有节点向四周扩散 */
          for (int i = 0; i < sz; i++) {
              Node cur = q.poll();
              /* 划重点：这里判断是否到达终点 */
              if (cur is target) {
                  return step;
              }
              /* 将 cur 的相邻节点加入队列 */
              for (Node x : cur.adj()) {
                  if (x not in visited) {
                      q.offer(x);
                      visited.add(x);
                  }
              }
          }
          /* 划重点：最后要更新步数 */
          step++;
      }
  }
  // 队列 q 就不说了，BFS 的核⼼数据结构； 
  // cur.adj() 泛指 cur 相邻的节点，⽐如说⼆维数组中，cur 上下左右四⾯的位置就是相邻节点； 
  // visited 的主要作⽤是防⽌⾛回头路，⼤部分时候都是必须的，但是像⼀般的⼆叉树结构，没有⼦节点到⽗节点的指针，不会⾛回头路就不需要visited。 
``` 
    
* **递归-实战题目**

 题号   |   名称  |  难度  |   分类  |   题解  
------ | ------ | ------ | ------- | ------- 
 [70](https://leetcode-cn.com/problems/climbing-stairs/ "爬楼梯") | [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/ "爬楼梯") | 简单 | 动态规划 | [尾递归、动态规划](https://ocykj2i631.feishu.cn/docs/doccnBX1W9TnHKx1RXREM0gdtrd#/ "爬楼梯") 
 [22](https://leetcode-cn.com/problems/generate-parentheses/ "括号生成") | [括号生成](https://leetcode-cn.com/problems/generate-parentheses/ "括号生成") | 中等 | 字符串、回溯算法 | [递归](https://ocykj2i631.feishu.cn/docs/doccnvxk0zmIoMiIha2cNXiCmHd#/ "括号生成") 
 [98](https://leetcode-cn.com/problems/validate-binary-search-tree/ "验证二叉搜索树") | [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/ "验证二叉搜索树") | 中等 | 树、DFS | [递归](https://ocykj2i631.feishu.cn/docs/doccnQJvf8ZnXUyUN0uZPfU2Wfe#/ "验证二叉搜索树") 
 [226](https://leetcode-cn.com/problems/invert-binary-tree/ "翻转二叉树") | [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/ "翻转二叉树") | 简单 | 树 | [递归](https://ocykj2i631.feishu.cn/docs/doccn0GQs5OBgXmSXK9wkq9eDil#/ "翻转二叉树") 
 [111](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/ "二叉树的最小深度") | [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/ "二叉树的最小深度") | 简单 | 树、BFS、DFS | [BFS、递归](https://ocykj2i631.feishu.cn/docs/doccnwvXDrQy9rmGwYVLyPcYAvh#/ "二叉树的最小深度") 
 [104](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/ "二叉树的最大深度") | [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/ "二叉树的最大深度") | 简单 | 树、DFS | [DFS](https://ocykj2i631.feishu.cn/docs/doccnckZcjeGXYnaklqmOK7NPJb#/ "二叉树的最大深度") 

* **DFS算法**其实就是**回溯算法**；
* **解决一个回溯问题，其实就是一个决策树的遍历过程**；
* 只需要思考**三个问题：**
    1.路径：也就是**已经做出的选择**；
    2.选择列表：也就是**你当前可以做的选择**；
    3.结束条件：也就是**到达决策树底层，无法再做选择的条件**。
* **经典问题**：全排列、N皇后问题；
* **回溯算法 代码模版：**

```java
  result = []
  def backtrack(路径, 选择列表):
      if 满足结束条件：
          result.add(路径)
          return
      for 选择 in 选择列表:
          做选择
          backtrack(路径, 选择列表)
          撤销选择
  # 核心就是 for循环里面的 递归，在递归调用之前 “做选择”
  # 在递归调用之后 “撤销选择”。
```

* 全排列问题：n 个不重复的数，全排列共有 n! 个。
* 比方说，给三个数[1, 2, 3]，我们可以一一穷举：
![决策树](https://ocykj2i631.feishu.cn/28a34072-a70e-4378-8aa4-1f637c64a95f)
