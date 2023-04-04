import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /**
     * #bfs
     * 1. 사이클 돌 때마다 숫자로 방문 체크
     * 2. 방문 하지 않은 노드라면 해당 사이클 숫자로 초기화
     * 3. 방문 했고, 같은 사이클이라면 안전구역 증가 (한번 돌아서 처음으로 재방문 한 것 이므로)
     * 4. 방문 했고, 다른 사이클이라면 안전구역 증가 x
     */
    static BufferedReader br;
    static StringTokenizer st;
    static int R,C;
    static int[][] graph;
    static int[][] visited;
    static ArrayDeque<Node> dq;
    static Queue<Node> tmpQ;
    static int check, safe;

    static final int[][] deltas = {{},{1,0},{-1,0},{0,-1},{0,1}}; // 오 왼 위 아
    public static void main(String[] args) throws Exception {
        input();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(visited[r][c] == 0) {
                    check++;
                    bfs(r, c);
                }
            }
        }

        System.out.println(safe);
    }

    static void bfs(int r,int c){
        dq = new ArrayDeque<>();
        dq.offerFirst(new Node(r,c));
        visited[r][c] = check;

        while(!dq.isEmpty()){
            Node cur = dq.pollFirst();

            int nr = cur.r + deltas[cur.dir][1];
            int nc = cur.c + deltas[cur.dir][0];
            if(visited[nr][nc] == 0){
                visited[nr][nc] = check;
                dq.offer(new Node(nr,nc));
            } else if(visited[nr][nc] == check){ // 이미 방문 했던 곳임. 안전구역 증가 시키고 탈출
                safe++;
                dq.clear();
            } else if(visited[nr][nc] != check){ // 이미 방문 했었고, 이전에 만든 안전 구역으로 들어 갈 수 있음, 탈출
                dq.clear();
            }
        }
    }


    static void input() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        graph = new int[R][C];
        visited = new int[R][C];
        dq = new ArrayDeque<>();
        tmpQ = new ArrayDeque<>();

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                char dir = s.charAt(c);
                switch(dir) { // deltas 배열 인덱스
                    case 'R':
                        graph[r][c] = 1;
                        break;
                    case 'L':
                        graph[r][c] = 2;
                        break;
                    case 'U':
                        graph[r][c] = 3;
                        break;
                    case 'D':
                        graph[r][c] = 4;
                        break;
                }
            }
        }
        check = 0; safe = 0;
    }

    static class Node{
        int r,c;
        int dir;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
            this.dir = graph[r][c];
        }
    }
}