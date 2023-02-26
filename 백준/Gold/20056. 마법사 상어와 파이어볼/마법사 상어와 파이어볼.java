import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, K;
    static ArrayList<Node>[][] graph;
    static Queue<Node> fireBalls;
    static Queue<Node> nextFireBalls;
    static boolean[][] isCombined;
    static int[][] deltas = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

    static class Node {
        int r, c;
        int m, s, d;

        public Node(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N][N];
        fireBalls = new LinkedList<>();
        nextFireBalls = new LinkedList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                graph[r][c] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Node fire = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            graph[fire.r][fire.c].add(fire);
            fireBalls.offer(fire);
        }

        // K번 만큼 이동
        for (int i = 0; i < K; i++) {
            // 파이어볼 이동
            moveFire();

            // 파이어볼 합체 후 4개로 분리
            combineAndDivide();
        }

        int ans = calcM();
        System.out.println(ans);
    }

    private static int calcM() {
        int sum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (graph[r][c].size() != 0) {
                    for (Node fire : graph[r][c]) {
                        sum += fire.m;
                    }
                }
            }
        }
        return sum;
    }

    private static void combineAndDivide() {
        isCombined = new boolean[N][N];
        while (!nextFireBalls.isEmpty()) {
            Node cur = nextFireBalls.poll();
            //이미 해당 위치는 합체가 되었을 경우 건너뜀
            if (isCombined[cur.r][cur.c]) {
                continue;
            }
            // 해당 위치에 파이어볼이 1개 이상 존재 할 경우
            if (graph[cur.r][cur.c].size() > 1) {
                isCombined[cur.r][cur.c] = true;
                // 파이어볼 합치기
                Node combined = null;
                boolean isDiag = false;
                int tmpM = 0, tmpS = 0;
                for (Node fire : graph[cur.r][cur.c]) {
                    // 모두 짝수가 아니거나 홀수가 아니면 대각선 방향으로
                    if (fire.d % 2 != cur.d % 2) isDiag = true;
                    tmpM += fire.m;
                    tmpS += fire.s;
                }
                tmpM /= 5;
                tmpS /= graph[cur.r][cur.c].size();
                graph[cur.r][cur.c].clear();

                // 질량 0이 아니면 큐에 넣어줌
                if (tmpM != 0) {
                    for (int i = 0; i < 8; i++) {
                        if (isDiag) i++;
                        combined = new Node(cur.r, cur.c, tmpM, tmpS, i);
                        graph[cur.r][cur.c].add(combined);
                        fireBalls.offer(combined);
                        if (!isDiag) i++;
                    }
                }
            } else {
                // 한개만 있을 경우 가만히
                fireBalls.offer(cur);
            }
        }
    }

    private static void moveFire() {
        while (!fireBalls.isEmpty()) {
            Node cur = fireBalls.poll();
            graph[cur.r][cur.c].remove(cur);
            int nr = Math.floorMod(cur.r + (deltas[cur.d][1] * cur.s), N);
            int nc = Math.floorMod(cur.c + (deltas[cur.d][0] * cur.s), N);
            // 이동 시킨 파이어볼 해당 위치에 add
            Node next = new Node(nr, nc, cur.m, cur.s, cur.d);
            graph[nr][nc].add(next);
            nextFireBalls.offer(next);
        }
    }
}