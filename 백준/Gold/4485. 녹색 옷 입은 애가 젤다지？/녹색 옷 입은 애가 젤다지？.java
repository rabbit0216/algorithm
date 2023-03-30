import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int ans;
    static int N;
    static int[][] graph;
    static PriorityQueue<Node> pq;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            ans = 0;
            graph = new int[N][N];
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            pq = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return Integer.compare(o1.price, o2.price);
                }
            });
            pq.offer(new Node(0,0,graph[0][0]));
            ans = bfs();
            sb.append("Problem ").append(test_case).append(":").append(' ').append(ans).append('\n');
            test_case++;
        }
        System.out.println(sb);
    }

    private static int bfs() {
        int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
        while(true){
            Node cur = pq.poll();
            if(cur.r == N-1 && cur.c == N-1) return cur.price;
            if(visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;

            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];
                if(!scope(nr,nc)) {
                    continue;
                }
                pq.offer(new Node(nr,nc, cur.price + graph[nr][nc]));
            }
        }
    }

    private static boolean scope(int r,int c){
        return r>=0 && r<N && c>=0 && c<N;
    }
    private static class Node {
        int r,c;
        int price;

        public Node(int r, int c, int price) {
            this.r = r;
            this.c = c;
            this.price = price;
        }
    }
}