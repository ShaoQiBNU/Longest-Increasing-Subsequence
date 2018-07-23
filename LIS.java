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