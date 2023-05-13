import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [키워드]
 * <p>
 * [풀이과정]
 * <p>
 * [입력]
 * [출력]
 *
 * @author
 * @performance
 * @category
 * @see
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[][] dp; // 회차 무기
    static int[][] weapon;
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        dp = new int[N][M]; weapon = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                weapon[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            dp[0][i] = weapon[0][i];
        }

        for (int i = 1; i < N; i++) { // 회차
            for (int j = 0; j < M; j++) { // 무기
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) { // 다른 무기들 계산해온 값
                    if(k == j) continue;
                    min = Math.min(min, dp[i-1][k]);
                }
                dp[i][j] = min + weapon[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            ans = Math.min(ans, dp[N-1][i]);
        }
        System.out.println(ans);
    }
}