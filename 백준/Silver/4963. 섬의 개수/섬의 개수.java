import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R, C;
    static int[][] graph;
    static boolean[][] visited;
    static int ans;
    static int[][] deltas = {{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1}};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        while(true){
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
            if(R == 0 && C == 0) break;

            graph = new int[R][C];
            visited = new boolean[R][C];

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if(visited[r][c]) continue;
                    if(graph[r][c] == 0) continue;
                    dfs(r,c);
                    ans++;
                }
            }
            sb.append(ans).append('\n');
        }
        bw.write(sb.toString());
        bw.flush(); bw.close();
    }

    private static void dfs(int r,int c){
        if(!scope(r,c)) return;
        if(graph[r][c] == 0) return;
        if(visited[r][c]) return;
        visited[r][c] = true;
        for (int dir = 0; dir < deltas.length; dir++) {
            int nr = r + deltas[dir][1];
            int nc = c + deltas[dir][0];
            dfs(nr,nc);
        }

    }

    private static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}