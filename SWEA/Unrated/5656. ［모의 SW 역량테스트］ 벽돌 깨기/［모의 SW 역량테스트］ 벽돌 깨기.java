import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, C, R;
    static int[][] graph;
    static int[][] orgGraph;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> queue;
    static int minAns;

    static class Node {
        int r, c;
        int cnt;

        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            graph = new int[R][C];
            orgGraph = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            orgGraph = copyGraph(graph);
            minAns = Integer.MAX_VALUE;
            makePerm(0, new int[N]);
            sb.append("#").append(test_case).append(" ").append(minAns).append("\n");
        }
        System.out.println(sb);
    }

    static int[][] copyGraph(int[][] graph) {
        int[][] copied = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                copied[r][c] = graph[r][c];
            }
        }
        return copied;
    }

    static void makePerm(int nthChoice, int[] choosed) {
        if (nthChoice == choosed.length) {
            graph = copyGraph(orgGraph);
            for (int i = 0; i < choosed.length; i++) {
                game(choosed[i]);
            }
            int ans = countBricks();
            minAns = Math.min(ans, minAns);
            return;
        }

        for (int i = 0; i < C; i++) {
            choosed[nthChoice] = i;
            makePerm(nthChoice + 1, choosed);
        }
    }

    private static void game(int ballPlace) {
        // 해당 열 제일 먼저 만나는 칸 사방 부수기 (graph[r][c] - 1 만큼)
        queue = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            if (graph[r][ballPlace] != 0) {
                queue.offer(new Node(r, ballPlace, graph[r][ballPlace] - 1));
                break;
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            graph[cur.r][cur.c] = 0;
            for (int[] delta : deltas) {
                int nr = cur.r;
                int nc = cur.c;
                int cnt = cur.cnt;
                while (cnt > 0) {
                    // 벽돌에 적힌 수 만큼 부수기
                    nr += delta[1];
                    nc += delta[0];
                    if (!scope(nr, nc)) break;
                    if (graph[nr][nc] > 1) {
                        queue.offer(new Node(nr, nc, graph[nr][nc] - 1));
                    }
                    graph[nr][nc] = 0;
                    --cnt;
                }
            }
        }

        // 공중에 떠있는 벽돌들 아래로 내리기
        for(int c=0;c<C;c++){
            int zeroCnt = 0;
            for(int r=R-1;r>=0;r--){
                if(graph[r][c] == 0){
                    zeroCnt++;
                } else if(graph[r][c] != 0 && zeroCnt > 0){
                    graph[r+zeroCnt][c] = graph[r][c];
                    graph[r][c] = 0;
                    r = r + zeroCnt;
                    zeroCnt = 0;
                }
            }
        }
    }

    private static int countBricks() {
        int sum = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (graph[r][c] != 0) {
                    ++sum;
                }
            }
        }
        return sum;
    }

    private static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}