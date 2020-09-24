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

## ***二、回溯***
* **DFS算法**其实就是**回溯算法**；
* **解决一个回溯问题，其实就是一个决策树的遍历过程**；
* 只需要思考**三个问题：**
    1.路径：也就是**已经做出的选择**；
    2.选择列表：也就是**你当前可以做的选择**；
    3.结束条件：也就是**到达决策树底层，无法再做选择的条件**。
* **经典问题**：全排列、51.N皇后问题；
* **回溯算法 代码模版：**

```java
  result = []
  def backtrack(路径, 选择列表):
      if 满足结束条件：
          result.add(路径)
          return
      for 选择 in 选择列表:
          # 做选择
          将该选择从选择列表移除 
          路径.add(选择)
          backtrack(路径, 选择列表)
          # 撤销选择 
          路径.remove(选择) 
          将该选择再加⼊选择列表
  # 核心就是 for循环里面的 递归，在递归调用之前 “做选择”
  # 在递归调用之后 “撤销选择”。
```

* 全排列问题：n 个不重复的数，全排列共有 n! 个。
* 比方说，给三个数[1, 2, 3]，我们可一一穷举：

![image](https://github.com/CodeFlow-Jun/algorithm016/blob/master/Week_03/%E5%86%B3%E7%AD%96%E6%A0%9101.png)

* 上面的这棵树就是 回溯算法的 **决策树**，我们会在每一个节点上去做决策。

![image](https://github.com/CodeFlow-Jun/algorithm016/blob/master/Week_03/%E5%86%B3%E7%AD%96%E6%A0%9102.png)

* 我们会在上图红色节点处做决策，可以选择 1 那条树枝，也可以选择 3 条树枝。
* 为啥只 能在 1 和 3 之中选择呢？因为 2 这个树枝在你⾝后，这个选择你之前做过了，⽽全排列是不允许重复使⽤数字的。
* 现在可以解答开头的⼏个名词： 
    1. [2] 就是「路径」，记录我们已经做过的选择； 
    2. [1,3] 就是「选择列表」，表⽰我们当前可以做出的选择；
    3. 「结束条件」就是遍历到树的底层，在这⾥就是选择列表为空的时候。
* 我们可以把「路径」和「选择列表」作为决策树上每个 节点的属性：
* 例如：根节点的的选择列表为[1, 2, 3]，路径为[];而子节点的选择列表为[]，路径为[2, 1, 3]等。
* 我们定义的 backtrack 函数其实就像⼀个指针，在这棵树上游⾛，同时要正确维护每个节点的属性，每当⾛到树的底层，其「路径」就是⼀个全排列。
* **写 backtrack 函数时，需要维护⾛过的「路径」和当前可以做的「选择列表」，当触发「结束条件」时，将「路径」记⼊结果集**。
* 全排列代码：
```java 
  List<List<Integer>> res = new LinkedList<>(); 
  
  /* 主函数，输⼊⼀组不重复的数字，返回它们的全排列 */ 
  List<List<Integer>> permute(int[] nums) { 
      // 记录「路径」 
      LinkedList<Integer> track = new LinkedList<>(); 
      backtrack(nums, track); 
      return res; 
  }
  
  // 路径：记录在 track 中 
  // 选择列表：nums 中不存在于 track 的那些元素 
  // 结束条件：nums 中的元素全都在 track 中出现 
  void backtrack(int[] nums, LinkedList<Integer> track) { 
      // 触发结束条件 
      if (track.size() == nums.length) { 
          res.add(new LinkedList(track)); 
          return; 
      }
      for (int i = 0; i < nums.length; i++) { 
          // 排除不合法的选择 
          if (track.contains(nums[i])) 
              continue; 
          // 做选择 
          track.add(nums[i]); 
          // 进⼊下⼀层决策树 
          backtrack(nums, track); 
          // 取消选择 
          track.removeLast(); 
      } 
  }
  // 这⾥稍微做了些变通，没有显式记录「选择列表」，⽽是通过 nums 和 track 推导出当前的选择列表
```
* 这个算法解决全排列不是很⾼效，因为对链表使⽤ contains ⽅法需要 O(N) 的时间复杂度。
* 有更好的⽅法通过交换元素达到⽬的。
* 回溯算法再怎么优化，时间复杂度也不会低于O(N!)
* 不像动态规划存在重叠⼦问题可以优化，回溯算法就是纯暴⼒穷举，复杂度⼀般都很⾼。
* **回溯-实战题目**

 题号   |   名称  |  难度  |   分类  |   题解  
------ | ------ | ------ | ------- | ------- 
 [51](https://leetcode-cn.com/problems/n-queens/ "N 皇后") | [N 皇后](https://leetcode-cn.com/problems/n-queens/ "N 皇后") | 困难 | 回溯 | [回溯](https://ocykj2i631.feishu.cn/docs/doccn4ikGOCGzBlBCF9pLTz0ZvP/ "N 皇后") 
