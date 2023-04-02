import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] graph;
    static Queue<Node> queue;
    static int ans;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        visited = new boolean[R][C][1<<6];
        queue = new ArrayDeque<>();
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                graph[r][c] = s.charAt(c);
                if (graph[r][c] == '0') {
                    queue.offer(new Node(r, c, 0));
                    visited[r][c][0] = true;
                }
            }
        }
        bfs();
        if(ans == 0) System.out.println(-1);
        else System.out.println(ans);
    }

    static void bfs() {
        int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (graph[cur.r][cur.c] == '1') {
                ans = cur.dist;
                return;
            }
            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];
                if (!scope(nr, nc)) continue;
                Node next = new Node(nr, nc, cur.dist + 1);
                next.keybit = cur.keybit;
                if (visited[nr][nc][cur.keybit]) continue;
                if (graph[nr][nc] == '#') continue; // 벽
                if (graph[nr][nc] >= 'A' && graph[nr][nc] <= 'F') { // 문이면
                    if((cur.keybit & (1<< (graph[nr][nc] - 'A'))) == 0) {// 열쇠 없으면 못지나감
                        continue;
                    }
                }
                if (graph[nr][nc] >= 'a' && graph[nr][nc] <= 'f') {
                    next.keybit |= 1 << (graph[nr][nc] - 'a');
                }
                queue.offer(next);
                visited[nr][nc][next.keybit] = true;
            }
        }
    }

    static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static class Node {
        int r, c;
        int dist;
        int keybit;

        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}