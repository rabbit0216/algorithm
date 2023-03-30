import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * 바이러스가 퍼지는 위치 저장 (bfs)
     * 벽 조합
     */

    static int R,C;
    static int[][] graph;
    static Queue<Node> queue;
    static boolean[][] visited;
    static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
    static int wall = 3;
    static int maxAns = Integer.MIN_VALUE;
    static ArrayList<Node> empty;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new int[R][C];
        visited = new boolean[R][C];
        queue = new ArrayDeque<>();
        empty = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                if(graph[r][c] == 2){
                    queue.offer(new Node(r,c));
                    visited[r][c] = true;
                } else if(graph[r][c] == 1){
                    visited[r][c] = true;
                } else {
                    empty.add(new Node(r,c));
                }
            }
        }

        makeCombi(0, new Node[3], 0);
        System.out.println(maxAns);
    }

    private static void bfs(boolean[][] visited){
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            // 확산
            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];
                if(!scope(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.offer(new Node(nr,nc));
            }
        }
    }

    private static void makeCombi(int nthChoice, Node[] choosed, int startIdx){
        if(nthChoice == choosed.length){
            boolean[][] v = copyGraph(visited);
            queue.clear();
            for (int i = 0; i < choosed.length; i++) {
                Node cur = choosed[i];
                v[cur.r][cur.c] = true;
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if(graph[r][c] == 2){
                        queue.offer(new Node(r,c));
                    }
                }
            }
            bfs(v);
            int ans = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if(!v[r][c]){
                        ans++;
                    }
                }
            }
            maxAns = Math.max(maxAns, ans);
            return;
        }
        for (int i = startIdx; i < empty.size(); i++) {
            choosed[nthChoice] = empty.get(i);
            makeCombi(nthChoice + 1 , choosed, i + 1);
        }
    }

    private static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }

    private static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    private static boolean[][] copyGraph(boolean[][] org){
        boolean[][] tmp = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                tmp[r][c] = org[r][c];
            }
        }
        return tmp;
    }
}