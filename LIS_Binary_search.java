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