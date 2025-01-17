import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static ArrayDeque<Node> queue;
    static int[][] ans;

    static class Node {
        int r,c;
        int dist;

        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        public Node() {

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        queue = new ArrayDeque<>();

        ans = new int[R][C];
        for(int[] arr : ans) {
            Arrays.fill(arr, -1);
        }

        Node goal = new Node();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    goal = new Node(i,j,0);
                }
                if(map[i][j] == 0) {
                    ans[i][j] = 0;
                }
            }
        }

        visited = new boolean[R][C];
       queue.offer(goal);
       visited[goal.r][goal.c] = true;

       bfs();

        StringBuilder sb = new StringBuilder();
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                sb.append(ans[r][c]).append(' ');
            }
            sb.append('\n');
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }
    static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
    private static void bfs() {
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            ans[cur.r][cur.c] = cur.dist;

            for(int dir = 0; dir<deltas.length; dir++) {
                int nr = cur.r + deltas[dir][0];
                int nc = cur.c + deltas[dir][1];

                if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if(visited[nr][nc] || map[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                queue.offer(new Node(nr,nc,cur.dist+1));
            }
        }
    }
}
