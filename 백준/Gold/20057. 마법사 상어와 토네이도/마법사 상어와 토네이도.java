import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] graph;
    static Node tornado;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int ans;
    static final int LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3;
    static int[][] spreadPercentageOrg = {{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, -1, -2, -3, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}};

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
        graph = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        tornado = new Node(N / 2, N / 2);
        ans = 0;
        int beforeSand = calcSand();
        moveTornado();
        int afterSand = calcSand();
        System.out.println(beforeSand - afterSand);
    }

    private static int calcSand(){
        int sum = 0;
        for(int r=0;r<N;r++){
            for(int c=0;c<N;c++){
                if(graph[r][c] > 0) sum += graph[r][c];
            }
        }
        return sum;
    }

    private static void moveTornado() {
        // 토네이도 이동
        int cnt = 1;
        int twice = 0;
        int nr = tornado.r;
        int nc = tornado.c;
        while (true) {
            if (!scope(nr, nc)) break;

            outer:
            for (int dir = 0; dir < deltas.length; dir++) {
                if (twice == 2) {
                    twice = 0;
                    cnt++;
                }
                for (int i = 0; i < cnt; i++) {
                    nr += deltas[dir][1];
                    nc += deltas[dir][0];
                    if (!scope(nr, nc)) {
                        break outer;
                    }
                    spread(nr, nc, dir);
                }
                twice++;
            }
        }
    }

    private static void spread(int nr, int nc, int dir) {
        if (graph[nr][nc] == 0) {
            return;
        }

        int[][] spreadPerc = new int[5][5];
        spreadPerc = copy5x5(spreadPercentageOrg);
        switch (dir) {
            case LEFT:
                break;
            case DOWN:
                spreadPerc = rotate(spreadPerc);
                break;
            case RIGHT:
                spreadPerc = rotate(rotate(spreadPerc));
                break;
            case UP:
                spreadPerc = rotate(rotate(rotate(spreadPerc)));
                break;
        }

        // spreadPerc 기준으로 모래 흩뿌리기
        // a 좌표 찾기
        Node remSandNode = null;
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(spreadPerc[r][c] == -1){
                    remSandNode = new Node(r,c);
                    break;
                }
            }
        }


        int sand = graph[nr][nc];
        int remSand = sand;
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(spreadPerc[r][c] > 0){
                    spreadPerc[r][c] = (int)(sand * (spreadPerc[r][c] * 0.01));
                    remSand -= spreadPerc[r][c];
                    if(remSand < 0) remSand = 0;
                }
            }
        }
        spreadPerc[remSandNode.r][remSandNode.c] = remSand;

        // 원본 배열에 복사
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(!scope(nr-2+r,nc-2+c)){
                    continue;
                }
                // x 위치
                if(spreadPerc[r][c] == -3){
                    graph[nr-2+r][nc-2+c] = 0;
                    continue;
                }
                // y 위치
                if(spreadPerc[r][c] == -2){
                    graph[nr-2+r][nc-2+c] = 0;
                    continue;
                }
                graph[nr-2+r][nc-2+c] += spreadPerc[r][c];
            }
        }
    }

    private static int[][] rotate(int[][] orgGraph) {
        int[][] tmp = copy5x5(spreadPercentageOrg);
        int orgC = 4;
        for (int r = 0; r < 5; r++) {
            int orgR = 0;
            for (int c = 0; c < 5; c++) {
                tmp[r][c] = orgGraph[orgR][orgC];
                orgR++;
            }
            orgC--;
        }
        return tmp;
    }

    private static int[][] copy5x5(int[][] org) {
        int[][] copied = new int[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                copied[r][c] = org[r][c];
            }
        }
        return copied;
    }

    private static boolean scope(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static boolean scope5x5(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}