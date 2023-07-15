import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[][] dp;
    static int[][] graph;
    static boolean[][] visited;
    static final int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
    static int ans = 1;

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        graph = new int[N][N];
        visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 1);
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ans = Math.max(dfs(r,c), ans);
            }
        }
        bw.write(ans+""); bw.flush(); bw.close();
    }

    static int dfs(int r, int c){
        if(visited[r][c]) return dp[r][c];
        visited[r][c] = true;

        for (int dir = 0; dir < deltas.length; dir++) {
            int nr = r + deltas[dir][1];
            int nc = c + deltas[dir][0];
            if(!scope(nr,nc)) continue;

            if(graph[nr][nc] > graph[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr,nc) + 1);
            }
        }
        return dp[r][c];
    }

    static boolean scope(int r,int c){
        return r>=0 && r<N && c>=0 && c<N;
    }
}