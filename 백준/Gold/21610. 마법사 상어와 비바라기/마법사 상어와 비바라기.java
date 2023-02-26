import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M;
    static int[][] graph;
    static boolean[][] vanish;
    static int[][] deltas = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static Queue<Node> clouds;
    static Queue<Node> nextClouds;


    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        clouds = new LinkedList<>();
        nextClouds = new LinkedList<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 처음 구름 위치 설정
        clouds.offer(new Node(N - 2, 0));
        clouds.offer(new Node(N - 2, 1));
        clouds.offer(new Node(N - 1, 0));
        clouds.offer(new Node(N - 1, 1));
        for (int i = 0; i < M; i++) {
            // 이동
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1; // 0부터 시작
            int moveCnt = Integer.parseInt(st.nextToken());
            // 구름 이동, 물 양 증가
            moveCloud(dir, moveCnt);

            // 물복사버그, nextClouds에 저장된 구름들 인접 대각선 체크
            copyWater();

            // 물의 양 2 이상, 구름 생성 (위에서 소멸 된 위치 제외 / visited true 면 continue)
            createCloud();
        }
        int ans = countWater();
        System.out.println(ans);
    }

    private static int countWater() {
        int sum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sum += graph[r][c];
            }
        }
        return sum;
    }

    private static void createCloud() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 구름이 있던 위치는 제외
                if (vanish[r][c]) continue;
                // 물 양이 2 이상일 경우 구름 생성
                if (graph[r][c] >= 2) {
                    graph[r][c] -= 2;
                    clouds.offer(new Node(r, c));
                }
            }
        }
    }

    private static void copyWater() {
        final int[][] diagDeltas = {{-1, -1}, {1, -1}, {1, 1}, {-1, 1}};
        while (!nextClouds.isEmpty()) {
            Node cur = nextClouds.poll();

            for (int dir = 0; dir < diagDeltas.length; dir++) {
                int nr = cur.r + diagDeltas[dir][1];
                int nc = cur.c + diagDeltas[dir][0];
                if (!copyScope(nr, nc)) continue;
                if (graph[nr][nc] > 0) {
                    // 대각선 위치에 물이 존재하면, 물 증가
                    graph[cur.r][cur.c] += 1;
                }
            }
        }
    }

    private static void moveCloud(int dir, int moveCnt) {
        // N + 1 = 0 -> (N+deltas[dir][1] % N)
        vanish = new boolean[N][N];
        while (!clouds.isEmpty()) {
            Node cur = clouds.poll();
            int nr = Math.floorMod(cur.r + (deltas[dir][1] * moveCnt), N);
            int nc = Math.floorMod(cur.c + (deltas[dir][0] * moveCnt), N);

            // 구름 위치 저장
            vanish[nr][nc] = true;
            nextClouds.offer(new Node(nr, nc));
            // 비 내림
            graph[nr][nc] += 1;
        }
    }


    private static boolean copyScope(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
