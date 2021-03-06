最长递增子序列
============

# 一. 问题描述

> 给定一个序列，求解它的最长递增子序列的长度。比如： arr[] = {3,1,4,1,5,9,2,6,5} 的最长递增子序列长度为4。即为：1,4,5,9。

# 二. 解决方法

## (一) 转换为LCS问题，时间复杂度O(n^2)
> 一种是转换为LCS问题，先将数组排序，然后排序后的结果与原数组做LCS，求不相邻的最长公共子串，时间复杂度为O(n^2)；


## (二) 动态规划，时间复杂度O(n^2)
> 另一种方法是采用动态规划，以dp[i]表示以i位结尾的最长递增子序列的长度，状态转移方程为：dp[0]=1，当arr[i]>arr[j]时，dp[i]=max(dp[i], dp[j]+1), j = 1,2,3,...,i-1。 从dp中求出最大值，即为最长递增子序列的长度。代码如下：

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

## (三) 二分，时间复杂度O(nlogn)

> 方法二中的dp[i]表示以arr[i]元素结尾的最长递增子序列(LIS)的长度，方法三中我们使用dp[i]保存所有长度为i+1的递增子序列中末尾元素的最小值。根据这个最小值，可以判断arr数组中的后续元素是否可以追加到既有IS中以形成更长的IS。由于dp数组是递增的，所以可以使用二分查找，所以复杂度为n(logn)。

**算法流程：**

- 1. dp[0]=arr[0]

- 2. 向后遍历arr，arr[i]，在dp中寻找第一个大于arr[i]的位置k，如果找到就将该位置更新为arr[i] —— dp[k]=arr[i]；否则，dp长度增一，并且新增位置的值就为arr[i]。由于dp是有序数组，查找位置采用二分法。

- 3. 使用一个全局变量来max存储更新最长递增子序列的长度。

**算法实例：**

```shell

arr: 3  1  4  1  5  9  2  6  5

1. dp[0]=arr[0]

dp:  3

2. 遍历 arr[1]=1

dp中第一个大于1的位置为0，因此 dp[0]=1

dp:  1

3. 遍历 arr[2]=4

dp中没有找到大于4的位置，因此 dp[1]=4

dp:  1  4

4. 遍历 arr[3]=1

由于dp中存在值为1，位置为0，因此 dp[0]=1

dp:  1  4

5. 遍历 arr[4]=5

dp中没有找到大于5的位置，因此 dp[2]=5

dp:  1  4  5

6. 遍历 arr[5]=9

dp中没有找到大于9的位置，因此 dp[3]=9

dp:  1  4  5  9

7. 遍历 arr[6]=2

dp中第一个大于2的位置为1，因此 dp[1]=2

dp:  1  2  4  9

8. 遍历 arr[7]=6

dp中第一个大于6的位置为3，因此 dp[3]=6

dp:  1  2  4  6

9. 遍历 arr[8]=5

dp中第一个大于5的位置为3，因此 dp[3]=5

dp:  1  2  4  5

最终最长递增子序列的长度为4

```

**注意： 这个 1,2,4,5 不是LIS字符串，比如本题例的LIS应该是1,4,5,9/6，2代表的意思是存储2位长度LIS的最小末尾是2， dp数组是存储对应长度LIS的最小末尾。有了这个末尾，我们就可以一个一个地插入数据。代码如下：**

```java
import java.util.*;

public class Stack {
    public static void main(String []args){

        int[] arr={3,1,4,1,5,9,2,6,5};

        int res=lengtdpOfLIS(arr);

        System.out.println(res);
    }
    
    // 寻找最长上升子串的长度
	public static int lengtdpOfLIS(int[] arr) {
        
        if(arr==null||arr.length==0)
            return 0;
        
        int[] dp = new int[arr.length];
        
        dp[0]=arr[0];

        int max=0;//最长子序列最右边的位置
        
        // 循环arr
        for(int i=1; i<arr.length; i++){
            
            // 插入dp末尾
            if(arr[i]>dp[max]){
                dp[++max]=arr[i];
            }
            
            // 寻找位置，替换
            else{
                
                int pos=findFirstBigger(dp,0,max,arr[i]);
                dp[pos]=arr[i];
            }
        }
		
        return max+1;
    }
    
    // 二分查找寻找位置
    public static int findFirstBigger(int[] dp, int left, int right, int target){
        
        if(left==right)
            return left;
        
        int mid=(left+right)/2;
        
        if(dp[mid]<target)
            
            return findFirstBigger(dp, mid+1, right, target);
        
        else
            
            return findFirstBigger(dp, left, mid, target);
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
            {   
                // 注意：身高相同但体重不同的人无法摞在一起
                if (actors[j][0] < actors[i][0] && actors[j][1] < actors[i][1])
                    dp[i] = max(dp[i], dp[j] + 1);
            }
            maxCount = max(maxCount, dp[i]);
        }
 
        return maxCount;
    }
};

```







