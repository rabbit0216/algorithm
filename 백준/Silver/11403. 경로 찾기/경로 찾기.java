import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[][] graph;
    static final int INF = 1_000_000;


    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(graph[r][c] == 0){
                    dp[r][c] = INF;
                } else {
                    dp[r][c] = graph[r][c];
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int from = 0; from < N; from++) {
                for (int to = 0; to < N; to++) {
                    dp[from][to] = Math.min(dp[from][i] + dp[i][to], dp[from][to]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(dp[r][c] == INF){
                    sb.append(0);
                } else {
                    sb.append(1);
                }
                sb.append(" ");
            }
            sb.append('\n');
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}