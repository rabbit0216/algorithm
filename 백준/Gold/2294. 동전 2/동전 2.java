import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer> coin = new ArrayList<>();
        int[] dp = new int [K+1];
        int INF = 1_000_000_000;
        Arrays.fill(dp, INF);
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            if(coin.contains(price)) continue;
            coin.add(price);
            if(price > K) {
                continue;
            }
            dp[price] = 1;
        }
//        System.out.println(coin);

        for (int i = 1; i <= K; i++) {
            int min = INF;
            for (int j = 0; j < coin.size(); j++) {
                if(i-coin.get(j) > 0 && dp[i] != 1) {
                    if(dp[i-coin.get(j)] != INF)
                        min = Math.min(min, dp[i-coin.get(j)] + 1);
                    dp[i] = min;
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        if(dp[K] >= INF) System.out.println(-1);
        else System.out.println(dp[K]);
    }
}