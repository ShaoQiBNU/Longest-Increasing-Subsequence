最长递增子序列
============

# 一. 问题描述

> 给定一个序列，求解它的最长递增子序列的长度。比如： arr[] = {3,1,4,1,5,9,2,6,5} 的最长递增子序列长度为4。即为：1,4,5,9。

# 二. 解决方法

> 有两种方式求解，一种是转换为LCS问题，先将数组排序，然后排序后的结果与原数组做LCS，求不相邻的最长公共子串，时间复杂度为O(n^2)；另一种方法是采用动态规划，以dp[i]表示以i位结尾的最长递增子序列的长度，状态转移方程为：dp[0]=1，当arr[i]>arr[j]时，dp[i]=max(dp[i], dp[j]+1), j = 1, 2, 3,...,i-1。 从dp中求出最大值，即为最长递增子序列的长度。代码如下：

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
