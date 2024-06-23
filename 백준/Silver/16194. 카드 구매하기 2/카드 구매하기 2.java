import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cardPrice = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            cardPrice[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = cardPrice[1];
        if (N == 1) {
            bw.write(cardPrice[1]+"");
            bw.flush();
            bw.close();
            return;
        }

        for (int i = 2; i <= N; i++) {
            dp[i] = cardPrice[i];
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[j] + dp[i-j], dp[i]);
            }
        }

        bw.write(dp[N]+"");
        bw.flush();
        bw.close();
    }
}