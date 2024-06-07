import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final int MOD = 1_000_000_009;
    private static final long[] dp = new long[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;
        makeDP();
        for (int test_case = 0; test_case < T; test_case++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static void makeDP() {
        for (int i = 7; i < 100_001; i++) {
            dp[i] = (dp[i - 2] + dp[i - 4] + dp[i - 6]) % MOD;
        }
    }
}