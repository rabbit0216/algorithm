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
    public static int[][] maze;
    public static int[][] dist;
    public static boolean[][] visited;
    public static Queue<Node> queue = new LinkedList<>();

    public static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;

        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            String tmp = br.readLine();
            for (int c = 0; c < M; c++) {
                maze[r][c] = tmp.charAt(c) - '0';
                if(maze[r][c] == 0) {
                    visited[r][c] = true;
                    dist[r][c] = -1;
                }
            }
        }
        queue.offer(new Node(0, 0));
        dist[0][0] = 1;
        bfs();
        System.out.println(dist[N-1][M-1]);
    }

    public static void bfs() {
        final int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int dir = 0; dir < move.length; dir++) {
                int nc = cur.c + move[dir][0];
                int nr = cur.r + move[dir][1];

                if(!scope(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                dist[nr][nc] = dist[cur.r][cur.c] + 1;
                visited[nr][nc] = true;
                queue.offer(new Node(nr,nc));
            }
        }
    }

    public static boolean scope(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}