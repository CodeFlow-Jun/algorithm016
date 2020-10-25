# 学习笔记

---------------------

## ***一、递归***
* **本质：寻找重复性**
* **递归代码模版：**
```java
  // 递归写法
  public void recur(int level, int param) { 
      // 递归终止条件
      if (level > MAX_LEVEL) { 
          // process result 
          return; 
      }
      // 当前层逻辑
      process(level, param); 
      // 下一层递归
      recur( level: level + 1, newParam); 
      
      // restore current status 
  }
```
## ***二、分治***
* **分治代码模板：**
```java
  // 递归写法
  private static int divide_conquer(Problem problem, ) {
      // recursion terminator
      if (problem == NULL) {
          int res = process_last_result();
          return res;     
      }
      // prepare data
      subProblems = split_problem(problem)
      
      // conquer subproblems
      res0 = divide_conquer(subProblems[0])
      res1 = divide_conquer(subProblems[1])
      
      // process and generate the final result
      result = process_result(res0, res1);
      
      // revert the current level states
      return result;
  }
```
## ***三、动态规划***
* **本质：动态规划 = 分治 + 最优子结构**
* **动态规划和递归或者分治没有本质上的区别（关键看有无最优子结构）**；
* **共性：找到重复子问题**；
* 差异性：最优子结构、中途可以**淘汰**次优解，将复杂度从指数级降到了多项式级别。
* **动态规划关键点：**
1. 最优子结构 opt[n] = best_of(opt[n-1], opt[n-2], ...)<br>
2. 存储中间状态：opt[i]<br>
3. 状态转移方程：（递推公式）<br>

* **动态规划实例解析：**
```java
  // 1. Fibonacci数列
  // 方法一：自顶向下
  // 时间复杂度：O(2^n)
  int fib(int n) {
      return n <= 1 ? n : fib(n - 1) + fib(n - 2);
  }
  // 优化：记忆化搜索（添加缓存）
  // 时间复杂度：O(n)
  int fib(int n, int[] memo) {
      // 递归终止条件
      if (n <= 1) {
          return n;
      }
      // 如果memo[n]没有被计算过，便重新开始创建
      if (memo[n] == 0) {
          memo[n] = fib(n - 1) + fib(n - 2);
      }
      return memo[n];
  }
  // 方法二：自底向上
  // 状态转移方程：
  // Fib: opt[i] = opt[n-1] + opt[n-2];
  // 时间复杂度：O(n)
  int fib(int n, int[] memo) {
      int[] a = new int[n];
      a[0] = 0, a[1] = 1;
      for (int i = 2; i <= n; i++) {
          a[i] = a[i - 1] + a[i - 2];
      }
      return a[n];
  }
  
  // 2. 路径计数
  // 分治
  int countPaths(boolean[][] grid, int row, int col) {
      if (!validSquare(grid, row, col)) 
          return 0;
      
      if (isAtEnd(grid, row, col) 
          return -1;
          
      return countPaths(grid, row + 1, col) + countPaths(grid, row, col + 1);
  }
  // 状态转移方程： 
  // opt[i][j] = opt[i + 1][j] + opt[i][j + 1](且判断a[i][j]是否为空地)
  int countPaths(int m, int n) {
      int[][] dp = new int[m][n];
      for (int i = 0; i < n; i++) dp[0][i] = 1;
      for (int i = 0; i < m; i++) dp[i][0] = 1;
      for (int i = 1; i < m; i++) {
          for (int j = 1; j < n; j++) {
              dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
          }
      }
      return dp[m - 1][n -1];
  }
  // 优化：降维
  int countPaths(int m, int n) {
      int[] cur = new int[n];
      Arrays.fill(cur, 1);
      for (int i = 1; i < m; i++) {
          for (int j = 1; j < n; j++) {
              cur[j] += cur[j - 1];
          }
      }
      return cur[n -1];
  }
```


