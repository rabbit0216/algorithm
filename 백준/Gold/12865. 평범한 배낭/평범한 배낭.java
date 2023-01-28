import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K + 1];
        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
        }

        //최대 무게부터 역순으로 계산
        for (int thingIdx = 1; thingIdx <= N; thingIdx++) {
            for (int weightIdx = K; weightIdx >= 1; weightIdx--) {
                if (weight[thingIdx] <= weightIdx) {
                    dp[weightIdx] = Math.max(dp[weightIdx], dp[weightIdx - weight[thingIdx]] + value[thingIdx]);
                }
            }
        }
        System.out.println(dp[K]);
    }
}
