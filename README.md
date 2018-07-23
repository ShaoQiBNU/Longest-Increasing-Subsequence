最长递增子序列
============

# 一. 问题描述

> 给定一个序列，求解它的最长递增子序列的长度。比如： arr[] = {3,1,4,1,5,9,2,6,5} 的最长递增子序列长度为4。即为：1,4,5,9。

# 二. 解决方法

> 有两种方式求解，一种是转换为LCS问题，先将数组排序，然后排序后的结果与原数组做LCS，求不相邻的最长公共子串，时间复杂度为O(n^2)；另一种方法是采用动态规划，以dp[i]表示以i位结尾的最长递增子序列的长度，状态转移方程为：dp[0]=1，当arr[i]>arr[j]时，dp[i]=max(dp[i], dp[j]+1), j = 1,2,3,...,i-1。 从dp中求出最大值，即为最长递增子序列的长度。代码如下：

```java
import java.util.*;

public class Stack {
    public static void main(String []args){

        int[] arr={3,1,4,1,5,9,2,6,5};
        int n=arr.length;

        int res=getHeight(arr,n);

        System.out.println(res);
    }
    public static int getHeight(int[] men, int n) {
        
        int max=0;
        int[] dp=new int[n];

        
        for(int i=0;i<n;i++){
            dp[i]=1;
        }

        // 循环遍历每一个元素，计算以arr[i]结尾的最长递增序列长度
        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){

                if(men[i]>men[j]){
                    // 求最大值
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }

            // 存储最大值
            if(max<dp[i]){

                max=dp[i];
            }
        }

        return max;
    }
}
```

# 三. 应用

> 叠罗汉是一个著名的游戏，游戏中一个人要站在另一个人的肩膀上。同时我们应该让下面的人比上面的人更高一点。已知参加游戏的每个人的身高，请编写代码计算通过选择参与游戏的人，我们最多能叠多少个人。注意这里的人都是先后到的，意味着参加游戏的人的先后顺序与原序列中的顺序应该一致。给定一个int数组men，代表依次来的每个人的身高。同时给定总人数n，请返回最多能叠的人数。保证n小于等于500。测试样例：[1,6,2,5,3,4],6，返回：4，即1,2,3,4。

> 解决方法是求最长递增子序列，代码如上。

> 叠罗汉是一个著名的游戏，游戏中一个人要站在另一个人的肩膀上。为了使叠成的罗汉更稳固，我们应该让上面的人比下面的人更轻一点。现在一个马戏团要表演这个节目，为了视觉效果，我们还要求下面的人的身高比上面的人高。请编写一个算法，计算最多能叠多少人，注意这里所有演员都同时出现。给定一个二维int的数组actors，每个元素有两个值，分别代表一个演员的身高和体重。同时给定演员总数n，请返回最多能叠的人数。保证总人数小于等于500。测试样例：[[1,2],[3,4],[5,6],[7,8]],4，返回：4。

> 解决方法是先对身高排序——升序，然后求体重的最长递增子序列的长度。代码如下：

```C++
class Stack {
public:
    int getHeight(vector<vector<int> > actors, int n) {
        int maxCount = 1;
        sort(actors.begin(),actors.end());
        vector<int> dp(n, 1);
        for (int i = 1; i < n; ++i)
        {
            for (int j = 0; j < i; ++j)
                if (actors[j][0] < actors[i][0] && actors[j][1] < actors[i][1]) dp[i] = max(dp[i], dp[j] + 1);
            maxCount = max(maxCount, dp[i]);
        }
 
        return maxCount;
    }
};

```







