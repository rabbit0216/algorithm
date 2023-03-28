import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        if (n == 1) {
            System.out.println(0);
            return;
        } else if (n == 2) {
            System.out.println(1);
            return;
        }
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= n; i++) {
            if (i % 6 == 0) {
                dp[i] = 1 + Math.min(Math.min(dp[i - 1], dp[i / 2]), dp[i / 3]);
                continue;
            }
            if (i % 3 == 0) {
                dp[i] = 1 + Math.min(dp[i - 1], dp[i / 3]);
                continue;
            }
            if (i % 2 == 0) {
                dp[i] = 1 + Math.min(dp[i - 1], dp[i / 2]);
                continue;
            }
            dp[i] = 1 + dp[i - 1];
        }
        System.out.println(dp[n]);
    }
}