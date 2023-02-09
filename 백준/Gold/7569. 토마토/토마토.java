import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int R, C, H;
    public static int ans;
    public static int tomatoSize;
    public static int countTomatoSize;
    public static Queue<Node> queue;
    public static int[][][] graph;
    public static boolean[][][] visited;
    public static int[][][] day;
    public static final int[][] move = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1},
            {0, 0, -1}};

    public static class Node {
        int r, c, h;

        Node(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();
        day = new int[R][C][H];
        graph = new int[R][C][H];
        visited = new boolean[R][C][H];
        tomatoSize = R * C * H;
        countTomatoSize = 0;
        ans = 0;

        for (int h = 0; h < H; h++) {
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    graph[r][c][h] = Integer.parseInt(st.nextToken());
                    if (graph[r][c][h] == -1) {
                        visited[r][c][h] = true;
                        tomatoSize--;
                    } else if (graph[r][c][h] == 1) {
                        queue.offer(new Node(r, c, h));
                        visited[r][c][h] = true;
                        countTomatoSize++;
                    }
                }
            }
        }

        if (tomatoSize == countTomatoSize) {
            System.out.println(0);
            return;
        }
        bfs();
        if (tomatoSize != countTomatoSize)
            ans = -1;
        System.out.println(ans);
    }

    public static boolean scope(int r, int c, int h) {
        return r >= 0 && r < R && c >= 0 && c < C && h >= 0 && h < H;
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int dir = 0; dir < move.length; dir++) {
                int nc = cur.c + move[dir][0];
                int nr = cur.r + move[dir][1];
                int nh = cur.h + move[dir][2];
                if (!scope(nr, nc, nh))
                    continue;
                if (visited[nr][nc][nh])
                    continue;
                queue.offer(new Node(nr,nc,nh));
                visited[nr][nc][nh] = true;
                graph[nr][nc][nh] = 1;
                day[nr][nc][nh] = day[cur.r][cur.c][cur.h] + 1;
                ans = Math.max(ans, day[nr][nc][nh]);
                countTomatoSize++;
            }
        }
    }
}