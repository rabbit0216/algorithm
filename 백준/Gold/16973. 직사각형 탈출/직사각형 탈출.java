import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C;
    static int[][] graph;
    static boolean[][] visited;
    static Queue<Node> queue;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Rectangle rect;
    static int ans;

    static class Rectangle {
        int height, width;
        int startR, startC;
        int finishR, finishC;

        public Rectangle(int height, int width, int startR, int startC, int finishR, int finishC) {
            this.height = height;
            this.width = width;
            this.startR = startR;
            this.startC = startC;
            this.finishR = finishR;
            this.finishC = finishC;
        }
    }

    static class Node {
        int r, c, dist;

        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new int[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];
        queue = new LinkedList<>();
        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        rect = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (graph[r][c] == 1) {
                    // 벽 자체를 직사각형 크기만큼 만들기
                    for (int y = r; y > r - rect.height && y >= 0; y--) {
                        for (int x = c; x > c - rect.width && x >= 0; x--) {
                            visited[y][x] = true;
                        }
                    }
                }
            }
        }
        Node startNode = new Node(rect.startR, rect.startC, 0);
        ans = -1;
        queue.add(startNode);
        bfs();
        System.out.println(ans);
    }

    private static void bfs() {
        // 좌상단 좌표로만 bfs 탐색
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.r == rect.finishR && cur.c == rect.finishC) {
                ans = cur.dist;
                break;
            }
            for (int[] delta : deltas) {
                int nr = cur.r + delta[1];
                int nc = cur.c + delta[0];
                if (!scope(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.offer(new Node(nr, nc, cur.dist + 1));
            }
        }
    }

    static boolean scope(int r, int c) {
        // 탐색 범위: 직사각형 크기 생각해서 설정, 좌상단 좌표 + 직사각형 h/w 가 격좌판 벗어나면 false
        return 1 <= r && r+rect.height-1 <= R && 1 <= c && c+ rect.width-1 <= C;
    }

}
