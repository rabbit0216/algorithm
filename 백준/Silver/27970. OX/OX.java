import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    /**
     * 가장 첫번째 O가 X가 되기 위해선 --> 2^(O앞의 X개수) 번 연산해야 함
     * <p>
     * 1. O인 인덱스 위치 큐에 저장
     * 2. DP에 마지막 O 까지의 2의 제곱 구해놓기
     * 3. 큐에서 O인 인덱스 빼면서 연산 횟수 ans 변수에 더해주기
     */

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int ans = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        int lastIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'O') {
                queue.offer(i);
                lastIdx = i;
            }
        }

        int[] dp = new int[lastIdx + 1];
        dp[0] = 1;
        for (int i = 1; i <= lastIdx; i++) { // 마지막 인덱스까지의 2 거듭제곱 연산횟수 저장
            dp[i] = (2 * dp[i - 1] % MOD) % MOD;
        }

        while (!queue.isEmpty()) {
            int leftO = queue.poll();
            int xs = leftO; // x 개수
            ans = (ans + dp[xs]) % MOD;
        }
        bw.write("" + ans);
        bw.flush();
        bw.close();
    }

//    private static int pow(long base, int exp, int mod){
//        if(exp == 1) return (int)base;
//        if(exp == 0) return 1;
//
//        long res = pow(base, exp/2, mod);
//        res = (res * res) % mod;
//        if(exp % 2 == 1) res = (res * base) % mod;
//        return (int)res;
//    }
}