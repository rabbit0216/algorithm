import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int R,C,T;
    static int[][] graph;
    static boolean[][][] visited;
    static Queue<Node> queue;

    static class Node{
        int r, c;
        int dist;
        boolean sword;

        public Node(int r, int c, int dist, boolean sword) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.sword = sword;
        }
    }

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[R][C];
        visited = new boolean[R][C][2];
        queue = new ArrayDeque<>();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        queue.offer(new Node(0,0,0, false));
        visited[0][0][0] = true;

        bfs();
        bw.flush(); bw.close();
    }

    static void bfs() throws IOException {
        int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.r == R-1 && cur.c == C-1){
                bw.write(cur.dist+"");
                return;
            }

            if(cur.dist == T) {
                break;
            }

            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];

                if(!scope(nr,nc)) continue;
                if(graph[nr][nc] == 2){
                    cur.sword = true;
                }
                if(!cur.sword){
                    if(visited[nr][nc][0]) continue;
                    if(graph[nr][nc] == 1) continue;
                    visited[nr][nc][0] = true;
                    queue.offer(new Node(nr,nc,cur.dist+1,false));
                } else {
                    if(visited[nr][nc][1]) continue;
                    visited[nr][nc][1] = true;
                    queue.offer(new Node(nr,nc,cur.dist+1,true));
                }
            }
        }
        bw.write("Fail");
    }

    private static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}