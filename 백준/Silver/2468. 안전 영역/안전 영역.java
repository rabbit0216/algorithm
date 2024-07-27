import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int ans = 0;
    static int highest = -1;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                highest = Math.max(highest, map[r][c]);
            }
        }

        for (int height = highest; height >= 0; height--) {
            visited = new boolean[N][N];
            int tmpAns = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(map[r][c] > height && !visited[r][c]) {
                        dfs(r,c,height);
                        tmpAns++;
                    }
                }
            }
            ans = Math.max(ans, tmpAns);
        }

        bw.write(ans+""); bw.flush(); bw.close();
    }

    static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
    static void dfs(int r,int c,int height) {
        visited[r][c] = true;

        for (int dir = 0; dir < deltas.length; dir++) {
            int nr = r + deltas[dir][0];
            int nc = c + deltas[dir][1];

            if(nr >= N || nr < 0 || nc >= N || nc < 0) continue;
            if(visited[nr][nc]) continue;

            visited[nr][nc] = true;
            if(map[nr][nc] <= height) continue;
            dfs(nr,nc,height);
        }
    }

}