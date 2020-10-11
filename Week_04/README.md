# 学习笔记

---------------------

## ***一、遍历搜索 DFS & BFS***
* 遍历搜素：在树(图/状态集)中寻找特定节点；
* 每个节点都要访问一次；
* 每个节点仅仅要访问一次；
* 对于节点的访问顺序不限，可分为：
> 深度优先搜索 DFS （不等该层循环结束便会进入下一层循环）
> 广度优先搜索 BFS （deque + 循环）
* **DFS模版：**
```java
  // 递归写法
  public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }


    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                results.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            allResults.add(results);
        }
        return allResults;
    }
``` 
## ***二、贪心算法***
* 贪心算法：是一种在每一步选择中都采取在当前状态下最好或最优的选择，从而希望导致结果是全局最好或最优的算法。
* 贪心：当下局部最优的选择；
* 回溯：更够回退；
* 动态规划：最有判断 + 回退（过程保存最优解）。

## ***三、二分查找***
* 采用二分查找的前提：
1. 目标函数是单调有序的；
2. 存在上下界；
3. 能够通过索引访问。
* **BFS算法 代码模版：**

```java
    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
``` 

* **牛顿迭代法 代码模版：**

```java
    long r = x;
    while (r * r > x)
        r = (r + x / r) / 2;
    return (int)r;
``` 


## ***四、本周作业***
 题号   |   名称  |  难度  |   分类  |   题解  
------ | ------ | ------ | ------- | ------- 
 [860](https://leetcode-cn.com/problems/lemonade-change/description/ "柠檬水找零") | [柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/description/ "柠檬水找零") | 简单 | 贪心 | - 
 [122](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/ "买卖股票的最佳时机 II") | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/ "买卖股票的最佳时机 II") | 简单 | 贪心、数组 | -
 [455](https://leetcode-cn.com/problems/assign-cookies/description/ "分发饼干") | [分发饼干](https://leetcode-cn.com/problems/assign-cookies/description/ "分发饼干") | 简单 | 贪心 | -
 [874](https://leetcode-cn.com/problems/walking-robot-simulation/description/ "模拟行走机器人") | [模拟行走机器人](https://leetcode-cn.com/problems/walking-robot-simulation/description/ "模拟行走机器人") | 简单 | 贪心 | -
