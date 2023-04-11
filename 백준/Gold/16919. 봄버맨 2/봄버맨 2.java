import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /**
     * <반례 존재>
     * 1초 : 그대로
     * 2초 : 모든 폭탄 채워진 상태
     * 3초 : 초기 폭탄 사방으로 터지고, 나머지는 다 채워진 상태
     * 4초 : 모든 폭탄 채워진 상태
     *
     * 5초 : 초기 폭탄 상태
     * 6초 : 모든 폭탄 채워진 상태
     * 7초 : 초기 폭탄 사방으로 터지고, 나머지는 다 채워진 상태
     * 8초 : 모든 폭탄 채워진 상태
     *
     * 9초 : 초기 폭탄 상태
     *
     * 2 2 5
     * O.
     * .O
     *
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] org;
    static char[][] graph3;
    static char[][] graph5;
    static char[][] full;
    static char[][] ans;
    static int R,C,N;
    static final char BOMB = 'O';
    static ArrayList<Node> bombs;
    static final int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());

        org = new char[R][C];
        graph3 = new char[R][C];
        graph5 = new char[R][C];
        full = new char[R][C];
        ans = new char[R][C];
        bombs = new ArrayList<>();
        
        for (int r = 0; r < R; r++) { // 미리 폭탄으로 채워넣기
            for (int c = 0; c < C; c++) {
                graph3[r][c] = BOMB;
                graph5[r][c] = BOMB;
                full[r][c] = BOMB;
            }
        }

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                org[r][c] = s.charAt(c);
                if(org[r][c] == BOMB) {
                    bombs.add(new Node(r,c));
                }
            }
        }

        after(graph3); // 3번 그래프
        addBomb(graph3);
        after(graph5); // 5번 그래프

        int res = N % 4;

        switch (res) {
            case 0:
            case 2:
                ans = full;
                break;
            case 1:
                ans = graph5;
                break;
            case 3:
                ans = graph3;
                break;
        }

        if(N == 1){
            ans = org;
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(ans[r][c]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void addBomb(char[][] graph){
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(graph[r][c] == BOMB) {
                    bombs.add(new Node(r,c));
                }
            }
        }
    }

    private static void after(char[][] graph){
        for (Node node : bombs) {
            graph[node.r][node.c] = '.';

            for (int i = 0; i < deltas.length; i++) {
                int nr = node.r + deltas[i][0];
                int nc = node.c + deltas[i][1];

                if(!scope(nr,nc)) continue;
                graph[nr][nc] = '.';
            }
        }
        bombs.clear();
    }

    private static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }

    private static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}