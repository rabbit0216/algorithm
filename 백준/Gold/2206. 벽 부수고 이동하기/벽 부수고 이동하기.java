import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int N, M;
    public static final int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int[][] graph;
    public static int[][] dist;
    public static boolean[][][] visited;
    public static Queue<Node> queue;

    public static class Node {
        int r, c;
        boolean destroy;
        int dist;

        Node(int r, int c, int dist, boolean destroy) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.destroy = destroy;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M][2];
        queue = new LinkedList<>();

        for (int r = 0; r < N; r++) {
            String tmp = br.readLine();
            for (int c = 0; c < M; c++) {
                graph[r][c] = tmp.charAt(c) - '0';
            }
        }
        queue.offer(new Node(0, 0, 1, true));
        visited[0][0][0] = true;
        dist[0][0] = 1;
        bfs();
        if (dist[N - 1][M - 1] == 0) System.out.println(-1);
        else System.out.println(dist[N - 1][M - 1]);
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.r == N-1 && cur.c == M-1) break;
            for (int dir = 0; dir < move.length; dir++) {
                int nr = cur.r + move[dir][1];
                int nc = cur.c + move[dir][0];

                if (!scope(nr, nc)) continue;
                if (graph[nr][nc] == 1) {
                    if (cur.destroy && !visited[nr][nc][0]) {
                        queue.offer(new Node(nr, nc, cur.dist + 1, false));
                        visited[nr][nc][1] = true;
                        dist[nr][nc] = cur.dist + 1;
                    }
                }
                if (graph[nr][nc] == 0) {
                    if (cur.destroy && !visited[nr][nc][0]) {
                        queue.offer(new Node(nr, nc, cur.dist + 1, true));
                        visited[nr][nc][0] = true;
                        dist[nr][nc] = dist[cur.r][cur.c] + 1;
                    } else if (!cur.destroy && !visited[nr][nc][1]) {
                        queue.offer(new Node(nr, nc, cur.dist + 1, false));
                        visited[nr][nc][1] = true;
                        dist[nr][nc] = cur.dist + 1;
                    }

                }

            }
        }
    }

    public static boolean scope(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}

