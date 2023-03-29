import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * #BFS
     * 1. 각 물건들 사이의 거리 계산 (비트 마스킹 도전?)
     * 2. 시작 위치로부터 각 물건들 사이 거리 계산
     * 3. 완탐, 순열 (물건)
     * 4. 구한 순열대로 차례로 거리 계산
     * 5. 가장 짧은 거리가 답
     * <p>
     * 풀고 비트마스킹으로 푼 풀이 보고, 비트마스킹으로도 풀어보기
     *
     * @param args
     */

    static int R, C;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static char[][] map;
    static boolean[][] visited;
    static final char WALL = '#', START = 'S', THING = 'X', END = 'E';
    static Node start, end;
    static Queue<Node> queue;
    static ArrayList<Node> things;
    static int[] thingsIdx;
    static ArrayList<Node>[] thingDist;
    static int[] startDist;
    static int[] endDist;
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        things = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                switch (map[r][c]) {
                    case WALL:
                        visited[r][c] = true;
                        break;
                    case START:
                        start = new Node(r, c, 0);
                        break;
                    case END:
                        end = new Node(r, c, -1);
                        break;
                    case THING:
                        things.add(new Node(r, c, 0));
                        break;
                }
            }
        }
        thingDist = new ArrayList[things.size()];
        thingsIdx = new int[things.size()];
        startDist = new int[things.size()];
        endDist = new int[things.size()];

        for (int i = 0; i < things.size(); i++) {
            thingsIdx[i] = i;
        }
        for (int i = 0; i < things.size(); i++) {
            thingDist[i] = new ArrayList<>();
        }
        queue = new ArrayDeque<>();

        calcThingsDist();
        if(things.size() == 0) {
            queue.offer(start);
            visited[start.r][start.c] = true;
            minDist = bfs(end.r, end.c);
        }
        else {
            makePerm(0,new int[things.size()], new boolean[things.size()]);
        }
        System.out.println(minDist);
    }

    static void makePerm(int nthChoice, int[] choosed, boolean[] visited){
        if(nthChoice == choosed.length){
            int before = choosed[0];
            int dist = startDist[before];
            for (int i = 1; i < choosed.length; i++) {
                int cur = choosed[i];
                dist += thingDist[before].get(cur).dist;
                before = choosed[i];
            }
            int end = choosed[choosed.length-1];
            dist += endDist[end];
            minDist = Math.min(dist, minDist);
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                choosed[nthChoice] = thingsIdx[i];
                makePerm(nthChoice+1,choosed,visited);
                visited[i] = false;
            }
        }
    }

    static void calcThingsDist() {
        for (int i = 0; i < things.size(); i++) {
            Node cur = things.get(i);
            for (int j = 0; j < things.size(); j++) { // 다른 물건들과의 거리
                Node another = things.get(j);
                if (i == j) { // 자기 자신은 dist = 0
                    thingDist[i].add(new Node(another.r, another.c, 0));
                } else {
                    int dist = calcDist(cur, another.r, another.c);
                    thingDist[i].add(new Node(another.r, another.c, dist));
                }
            }
            // 시작점과의 거리
            startDist[i] = calcDist(cur, start.r, start.c);

            // 끝점과의 거리
            endDist[i] = calcDist(cur, end.r, end.c);
        }
    }

    private static int calcDist(Node cur, int r, int c) {
        queue.clear();
        visited = new boolean[R][C];
        visited[cur.r][cur.c] = true;
        queue.offer(new Node(cur.r, cur.c, 0));
        return bfs(r, c);
    }

    private static int bfs(int r, int c) {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.r == r && cur.c == c) {
                return cur.dist;
            }
            for (int[] delta : deltas) {
                int nr = cur.r + delta[1];
                int nc = cur.c + delta[0];
                if (!scope(nr, nc)) continue;
                if (map[nr][nc] == WALL) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.offer(new Node(nr, nc, cur.dist + 1));
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static class Node {
        int r, c;
        int dist;

        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    '}';
        }
    }
}