import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int R, C, K, x, y;
    static int[][] graph;
    static int[] diceStatus; // 주사위 각 면에 적힌 숫자 저장
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static Node dice; // 현재 상태 저장
    static boolean isOut;
    static final int EAST = 0, WEST = 1, NORTH = 2, SOUTH = 3;

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
        sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        diceStatus = new int[7];
        graph = new int[R][C];
        dice = new Node(x, y);
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken()) - 1; // 0부터 시작
            move(command);
        }
        System.out.println(sb);
    }

    private static void move(int command) {
        int r = dice.r + deltas[command][1];
        int c = dice.c + deltas[command][0];

        // 범위 벗어나면 무시
        if (!scope(r, c)) {
            return;
        }

        int bottom = diceStatus[6];
        // 해당 방향으로 주사위 굴리기
        switch(command){
            case EAST:
                diceStatus[6] = diceStatus[3];
                diceStatus[3] = diceStatus[1];
                diceStatus[1] = diceStatus[4];
                diceStatus[4] = bottom;
                break;
            case WEST:
                diceStatus[6] = diceStatus[4];
                diceStatus[4] = diceStatus[1];
                diceStatus[1] = diceStatus[3];
                diceStatus[3] = bottom;
                break;
            case NORTH:
                diceStatus[6] = diceStatus[2];
                diceStatus[2] = diceStatus[1];
                diceStatus[1] = diceStatus[5];
                diceStatus[5] = bottom;
                break;
            case SOUTH:
                diceStatus[6] = diceStatus[5];
                diceStatus[5] = diceStatus[1];
                diceStatus[1] = diceStatus[2];
                diceStatus[2] = bottom;
                break;
        }

        // 이동한 칸에 적혀있는 수가 0이면, 주사위 바닥면에 쓰인 수 복사
        if (graph[r][c] == 0) {
            graph[r][c] = diceStatus[6];
        } else { // 0이 아니면, 칸에 쓰여 있는 수가 주사위 바닥면에 복사, 해당 칸 0으로 초기화
            diceStatus[6] = graph[r][c];
            graph[r][c] = 0;
        }
        dice.r = r;
        dice.c = c;
        int ans = diceStatus[1];
        sb.append(ans).append('\n');
    }

    private static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}