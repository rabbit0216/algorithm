import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            int floor = Integer.parseInt(br.readLine());
            int ho = Integer.parseInt(br.readLine());
            int[][] dp = new int[floor + 1][ho + 1];
            // 초기화
            for (int f = 0; f <= floor; f++) {
                dp[f][1] = 1;
            }
            for (int h = 1; h <= ho; h++) {
                dp[0][h] = h;
            }
            
            // 저장
            for (int f = 1; f <= floor; f++) {
                for (int h = 2; h <= ho; h++) {
                    dp[f][h] = dp[f][h-1] + dp[f-1][h];
                }
            }
            int ans = dp[floor][ho];
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}