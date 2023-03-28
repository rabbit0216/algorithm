import java.util.Scanner;

public class Main {
    static int cnt = 0;
    static final int MOD = 10_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        if(n == 1) {
            System.out.println(1);
            return ;
        }
        dp[1]=  1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = (dp[i-1]+dp[i-2]) % MOD;
        }
        System.out.println(dp[n]);
    }
}