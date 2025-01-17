import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class Solution {
 
    static int n;
    static int[] weight;
    static int[] value;
    static int maxWeight;
 
    static Integer[][] dp;
 
    // Top Down 방식
    static int knapsack_td(int i, int w){
        if(i < 0){
            return 0;
        }
        if(dp[i][w] == null){
            if(weight[i] > w){
                dp[i][w] = knapsack_td(i - 1, w);
            }
            else{
                dp[i][w] = Math.max(knapsack_td(i - 1, w), knapsack_td(i - 1, w - weight[i]) + value[i]);
            }
        }
        return dp[i][w];
    }
 
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int tcase = parseInt(br.readLine());
         
        for(int i=1;i<=tcase;i++) {
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            maxWeight = parseInt(st.nextToken());
 
            weight = new int[n+1];
            value = new int[n+1];
            dp = new Integer[n+1][maxWeight + 1];
            for(int j = 1; j <= n; j++){
                st = new StringTokenizer(br.readLine());
                weight[j] = parseInt(st.nextToken());
                value[j] = parseInt(st.nextToken());
            }
 
            System.out.println("#"+i+" "+knapsack_td(n, maxWeight));
        }
         
 
 
    }
 
}
