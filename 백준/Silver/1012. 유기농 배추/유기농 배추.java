import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int R, C, K;
    public static int[][] graph;
    public static int[][] dist;
    public static boolean[][] visited;
    public static Queue<Node> adj;
    public static Queue<Node> all;
    public static int ans;

    public static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new int[R][C];
            visited = new boolean[R][C];
            ans = 0;
            adj = new LinkedList<>();
            all = new LinkedList<>();
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                graph[r][c] = 1;
                all.offer(new Node(r, c));
            }
            bfs();
            System.out.println(ans);
        }
    }

    public static void bfs() {
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!all.isEmpty()) {
            Node cur = all.poll();
            // 이미 방문했던 배추는 건너뛰기 (ans++ 안됨)
            if(visited[cur.r][cur.c]) continue;
            while (true) {
                // 현재 배추와 인접한 배추들 탐색하여 인접큐에 넣어주기
                // 방문 여부 갱신을 통해 전체 배추 큐를 탐색 할 때 이미 방문한 배추는 카운팅 하지 않게끔 처리
                for (int dir = 0; dir < move.length; dir++) {
                    int nr = cur.r + move[dir][1];
                    int nc = cur.c + move[dir][0];

                    if (!scope(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    if (graph[nr][nc] == 1) {
                        adj.offer(new Node(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
                if (adj.isEmpty()) {
                    break;
                }
                cur = adj.poll();
            }
            ans++;
        }
    }

    public static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}