import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int R, C;
    static char[][] graph;
    static boolean[][] visited;
    static final char ICE = 'X', SWAN = 'L', WATER = '.';
    static Queue<Node> waterQ;
    static Queue<Node> nextWaterQ;
    static Node swan2;
    static Queue<Node> swanQ, swanNextQ;
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int ans = 0;
    static boolean isEnd = false;

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        visited = new boolean[R][C];

        waterQ = new ArrayDeque<>();
        nextWaterQ = new ArrayDeque<>();
        swanQ = new ArrayDeque<>();
        swanNextQ = new ArrayDeque<>();

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                graph[r][c] = s.charAt(c);
                if (graph[r][c] != ICE) {
                    waterQ.offer(new Node(r, c));
                }
                if (graph[r][c] == SWAN) {
                    if (swanQ.isEmpty()) {
                        swanQ.offer(new Node(r, c));
                        visited[r][c] = true;
                    } else {
                        swan2 = new Node(r, c);
                    }
                }
            }
        }

        while (true) {
            // 백조 이동
            moveSwan();
            if (isEnd) break;

            // 얼음 녹기
            meltIce();
            ans++;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
    }

    private static void moveSwan() {
        while (!swanQ.isEmpty()) {
            Node cur = swanQ.poll();

            if (cur.r == swan2.r && cur.c == swan2.c) {
                isEnd = true;
                return;
            }

            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];

                if (!scope(nr, nc)) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if (graph[nr][nc] == ICE) {
                    swanNextQ.offer(new Node(nr, nc));
                } else {
                    swanQ.offer(new Node(nr, nc));
                }
            }
        }

        while (!swanNextQ.isEmpty()) {
            swanQ.offer(swanNextQ.poll());
        }
    }

    private static void meltIce() {
        while (!waterQ.isEmpty()) {
            Node cur = waterQ.poll();

            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];
                if (!scope(nr, nc)) continue;

                if (graph[nr][nc] == ICE) {
                    graph[nr][nc] = WATER;
                    nextWaterQ.offer(new Node(nr, nc));
                }
            }
        }

        // nextQ에 저장된 데이터 다시 이동
        while (!nextWaterQ.isEmpty()) {
            waterQ.offer(nextWaterQ.poll());
        }
    }

    private static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}