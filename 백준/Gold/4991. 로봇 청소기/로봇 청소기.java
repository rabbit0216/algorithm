import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int W, H;
    static char[][] graph;
    static Node robot;
    static Queue<Node> queue;
    static boolean[][] visited;
    static int dust;
    static final char DUST = '*', FURNITURE = 'x', ROBOT = 'o', CLEAN = '.';
    static ArrayList<Node> dusts;
    static Node[][] dustDist;
    static Node[] robotDist;
    static int minDist;
    static StringBuilder sb;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Node {
        int h, w;
        int dist;

        public Node(int h, int w, int dist) {
            this.h = h;
            this.w = w;
            this.dist = dist;
        }
    }

    /**
     * 먼지의 수는 10개 이하
     * 청소 할 먼지 순서 완탐 -> 가장 최단 거리가 정답
     * 순열 돌때마다 bfs 하니까 시간초과 -> 로봇과 먼지 거리, 먼지간의 거리 미리 계산해서 저장
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                System.out.println(sb);
                return;
            }
            graph = new char[H][W];
            queue = new LinkedList<>();
            visited = new boolean[H][W];
            dust = 0;
            dusts = new ArrayList<>();
            for (int h = 0; h < H; h++) {
                String s = br.readLine();
                for (int w = 0; w < W; w++) {
                    graph[h][w] = s.charAt(w);
                    if (graph[h][w] == ROBOT) {
                        robot = new Node(h, w, 0);
                    } else if (graph[h][w] == DUST) {
                        dust++;
                        Node cur = new Node(h, w, 0);
                        dusts.add(cur);

                    }
                }
            }

            dustDist = new Node[dusts.size()][dusts.size()];
            robotDist = new Node[dusts.size()];
            minDist = Integer.MAX_VALUE;

            cleaning();

            if (minDist == Integer.MAX_VALUE) sb.append(-1);
            else sb.append(minDist);
            sb.append('\n');
        }
    }

    private static void cleaning(){
        calcDustDist();
        calcRobotDist();
        makePerm(0, new int[dust], new boolean[dust]);
    }

    // 로봇이랑 먼지 거리 계산
    private static void calcRobotDist() {
        for (int i = 0; i < dusts.size(); i++) {
            visited = new boolean[H][W];
            Node dust = dusts.get(i);
            queue.offer(dust);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nh = cur.h + deltas[dir][1];
                    int nw = cur.w + deltas[dir][0];
                    if (!scope(nh, nw)) continue;
                    if (visited[nh][nw]) continue;
                    if (graph[nh][nw] == FURNITURE) continue;
                    if (nh == dust.h && nw == dust.w) continue; // 자기 자신 제외
                    Node next = new Node(nh, nw, cur.dist + 1);
                    if (graph[nh][nw] == ROBOT) {
                        robotDist[i] = next;
                        queue.clear(); // 큐 탈출
                        break;
                    }
                    visited[nh][nw] = true;
                    queue.offer(next);
                }
            }
        }
    }

    // 먼지들끼리 떨어진 거리 계산
    private static void calcDustDist() {
        queue = new ArrayDeque<>();
        for (int i = 0; i < dusts.size(); i++) {
            visited = new boolean[H][W];
            Node dust = dusts.get(i);
            queue.offer(dust);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nh = cur.h + deltas[dir][1];
                    int nw = cur.w + deltas[dir][0];
                    if (!scope(nh, nw)) continue;
                    if (visited[nh][nw]) continue;
                    if (graph[nh][nw] == FURNITURE) continue;
                    if (nh == dust.h && nw == dust.w) continue; // 자기 자신 제외
                    Node next = new Node(nh, nw, cur.dist + 1);
                    if (graph[nh][nw] == DUST) { // 현재 위치가 자신을 제외한 다른 먼지면 해당하는 번호에 현재 위치 및 거리 저장
                        for (int dustIdx = 0; dustIdx < dusts.size(); dustIdx++) {
                            if (dusts.get(dustIdx).h == nh && dusts.get(dustIdx).w == nw) {
                                dustDist[i][dustIdx] = next;
                            }
                        }
                    }
                    visited[nh][nw] = true;
                    queue.offer(next);
                }
            }
        }
    }

    // 순회할 먼지 순서 결정 및 거리 계산
    private static void makePerm(int nthChoice, int[] checked, boolean[] tmpVisited) {
        if (nthChoice == checked.length) {
            dust = dusts.size();
            solution(checked);
            return;
        }

        for (int i = 0; i < tmpVisited.length; i++) {
            if (!tmpVisited[i]) {
                tmpVisited[i] = true;
                checked[nthChoice] = i;
                makePerm(nthChoice + 1, checked, tmpVisited);
                tmpVisited[i] = false;
            }
        }
    }

    private static void solution(int[] checked) {
        // 접근할 수 없는 곳 이면 탈출
        if (robotDist[checked[0]] == null) {
            return;
        }
        int dist = robotDist[checked[0]].dist;
        dust--;
        int checkedIdx = 0;
        int prev = checked[0];
        int next = 0;
        // 먼지 한개 이상일 경우 다음 먼지 위치 지정
        if (checkedIdx < checked.length - 1) {
            next = checked[++checkedIdx];
        }
        // 들어온 먼지 순서에 따라 각 먼지간의 거리 계산
        for (int i = 0; i < dusts.size(); i++) {
            if (dustDist[prev][next] != null) {
                dust--;
                dist += dustDist[prev][next].dist;
                prev = next;
                if (checkedIdx == checked.length - 1) break;
                next = checked[++checkedIdx];
            }
        }
        // 먼지 다 청소하면 최단거리 갱신
        if (dust == 0) {
            minDist = Math.min(dist, minDist);
        }
    }

    private static boolean scope(int h, int w) {
        return h >= 0 && h < H && w >= 0 && w < W;
    }
}