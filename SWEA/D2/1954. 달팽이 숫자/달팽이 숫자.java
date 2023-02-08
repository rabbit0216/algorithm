import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static final int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static boolean[][] visited;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder output = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            output.append("#").append(test_case).append("\n");
            N = Integer.parseInt(st.nextToken());
            int[][] ans = new int[N][N];
            visited = new boolean[N][N];
            int dir = 0;
            int nr = 0;
            int nc = 0;
            ans[0][0] = 1;
            visited[0][0] = true;
            int num = 2;
            while (true) {
                if (num > N * N) break;
                nc += move[dir][0];
                nr += move[dir][1];
                if (!scope(nr, nc) || visited[nr][nc]) {
                    nc -= move[dir][0];
                    nr -= move[dir][1];
                    dir = (dir + 1) % 4;
                    continue;
                }
                ans[nr][nc] = num;
                num++;
                visited[nr][nc] = true;
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    output.append(ans[r][c]);
                    if (c != N - 1) output.append(" ");
                }
                output.append("\n");
            }
        }
        System.out.println(output);
    }

    public static boolean scope(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}