import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, appleNum, changeNum;
    static Deque<Node> dq;
    static Queue<Rotate> dirInfo;
    static int[][] graph;
    static boolean[][] visited;
    static final int APPLE = -1;
    static final int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
    static int ans;

    static class Node {
        int r, c;
        int dir;
        int time;

        public Node(int r, int c, int dir, int time) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.time = time;
        }
    }

    static class Rotate{
        int time;
        String dir;

        public Rotate(int time, String dir) {
            this.time = time;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); appleNum = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for (int i = 0; i < appleNum; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); int c = Integer.parseInt(st.nextToken());
            graph[r][c] = APPLE;
        }

        changeNum = Integer.parseInt(br.readLine());
        dirInfo = new ArrayDeque<>();
        for (int i = 0; i < changeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            dirInfo.offer(new Rotate(time, dir));
        }

        dq = new ArrayDeque<>();
        visited[1][1] = true;
        dq.addFirst(new Node(1,1,0,0));
        solution();
        System.out.println(ans);
    }

    private static void solution() {
        Rotate rotate = dirInfo.poll();
        int time = 0;
        while(true){
            Node cur = dq.peekFirst();
            
            // ?????? ??????
            if(rotate != null && cur.time == rotate.time) {
                if(rotate.dir.equals("D")){ // ??????????????? ??????
                    cur.dir = (cur.dir + 1) % 4;
                } else { // ???????????? ??????
                    cur.dir = (cur.dir + 3) % 4;
                }
                rotate = dirInfo.poll();
            }

            time++;
            int nr = cur.r + deltas[cur.dir][1];
            int nc = cur.c + deltas[cur.dir][0];

            if(!scope(nr,nc)) { // ?????? ?????? ?????? ???
                ans = time;
                return;
            }
            if(visited[nr][nc]){ // ?????? ?????? ?????? ???
                ans = time;
                return;
            }
            if (graph[nr][nc] != APPLE) { // ?????? ?????? ?????? ?????? ????????? ?????? ??????
                Node tail = dq.pollLast();
                visited[tail.r][tail.c] = false; // ?????? ?????? ?????? ?????? ??????
            } else { // ?????? ????????? ?????????
                graph[nr][nc] = 0;
            }
            visited[nr][nc] = true;
            dq.addFirst(new Node(nr,nc,cur.dir,time));
        }
    }


    private static boolean scope(int r,int c){
        return r>=1 && r<=N && c>=1 && c<=N;
    }
}
