import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] seat;
    static final int MAX_TIME = 1_000_011;

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        seat = new int[MAX_TIME];

        int end = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            end = Math.max(E, end);
            seat[S]++;
            if (E < MAX_TIME-1) {
                seat[E + 1]--;
            }
        }

        for (int i = 1; i <= end+1; i++) {
            seat[i] += seat[i-1];
        }

        int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int time = Integer.parseInt(st.nextToken());
            int ans = seat[time];
            sb.append(ans).append('\n');
        }
        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}