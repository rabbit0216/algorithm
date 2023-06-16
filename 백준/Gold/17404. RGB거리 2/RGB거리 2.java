import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        final int INF = 1_000_000_000;

        int[][] home = new int[N][3];
        int[][] dp = new int[N][3];
        int[] minPaint = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == j) {
                    dp[0][j] = home[0][j];
                } else {
                    dp[0][j] = INF;
                }
            }

            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + home[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + home[j][1];
                dp[j][2] = Math.min(dp[j-1][1], dp[j-1][0]) + home[j][2];
            }
            if(i == 0) { // 첫 집이 red
                minPaint[i] = Math.min(dp[N-1][1], dp[N-1][2]);
            } else if(i == 1){ // 첫 집이 green
                minPaint[i] = Math.min(dp[N-1][0], dp[N-1][2]);
            } else { // 첫 집이 blue
                minPaint[i] = Math.min(dp[N-1][0], dp[N-1][1]);
            }
        }

        int ans = Math.min(minPaint[0], Math.min(minPaint[1], minPaint[2]));
        bw.write(ans+"");
        bw.flush(); bw.close();
    }
}
