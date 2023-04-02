import sun.reflect.annotation.ExceptionProxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /**
     * 1. 가장자리들 visit 처리하고, 큐에 넣기
     * 2. 가장자리 주변에 치즈 아닌 빈 공간 enque
     * 3. 다른 큐에 해당 공간들 enque
     * 4. 해당 공간들 탐색해서, 치즈 만나면 해당 위치 제거
     * 5. 치즈 위치들 다른 큐에 저장 ,반복
     */
    static Queue<Node> queue;
    static Queue<Node> tmpQ;
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C;
    static int[][] graph;
    static boolean[][] visited;
    static int time;
    static int remCheese;
    static final int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        input();
        solution();
        System.out.println(time);
        System.out.println(remCheese);
    }

    static void solution(){
        while(!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                for (int dir = 0; dir < deltas.length; dir++) {
                    int nr = cur.r + deltas[dir][1];
                    int nc = cur.c + deltas[dir][0];

                    if (!scope(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    if(graph[nr][nc] == 1) {
                        tmpQ.offer(new Node(nr,nc));
                        visited[nr][nc] = true;
                        graph[nr][nc] = 0;
                        continue;
                    }
                    visited[nr][nc] = true;
                    queue.offer(new Node(nr,nc));
                }
            }

            if(tmpQ.size() != 0) {
                remCheese = tmpQ.size();
                time++;
            }
            copyQueue();
        }
    }

    static void copyQueue() {
        queue.clear();
        while (!tmpQ.isEmpty()) {
            queue.offer(tmpQ.poll());
        }
    }

    static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static void input() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new int[R][C];
        visited = new boolean[R][C];
        time = 0;
        remCheese = 0;
        queue = new ArrayDeque<>();
        tmpQ = new ArrayDeque<>();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                if (r == 0 || c == 0 || r == R - 1 || c == C - 1) { // 가장자리 넣어주기
                    queue.offer(new Node(r, c));
                    visited[r][c] = true;
                }
            }
        }
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}