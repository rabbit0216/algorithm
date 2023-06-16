import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        int[] trace = new int[n + 1];

        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = 1 + dp[i - 1];
            trace[i] = i-1;

            if(i % 2 == 0 && dp[i] > dp[i/2]) {
                dp[i] = 1 + dp[i/2];
                trace[i] = i/2;
            }

            if(i % 3 == 0 && dp[i] > dp[i/3]){
                dp[i] = 1 + dp[i/3];
                trace[i] = i/3;
            }
        }

        int i = n;
        StringBuilder sb = new StringBuilder();
        while(i > 1){
            sb.append(i).append(" ");
            i = trace[i];
        }
        sb.append("1");
        System.out.println(dp[n]);
        System.out.println(sb);
    }
}