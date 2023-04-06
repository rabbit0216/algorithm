import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * #bfs ?
     * 1. 색종이 영역 1로 채우기
     * 2. 1인 영역 상하좌우 살폈을 때, 0이면 둘레에 추가
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] graph;
    static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        graph = new int [101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken()); int C = Integer.parseInt(st.nextToken());

            for (int r = R; r < R + 10; r++) {
                for (int c = C; c < C + 10; c++) {
                    graph[r][c] = 1;
                }
            }
        }

        int ans = 0;
        for (int r = 0; r <= 100; r++) {
            for (int c = 0; c <= 100; c++) {
                if(graph[r][c] == 1){
                    for (int dir = 0; dir < deltas.length; dir++) {
                        int nr = r + deltas[dir][1];
                        int nc = c + deltas[dir][0];

                        if(!scope(nr,nc)) continue;
                        if(graph[nr][nc] == 0) ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static boolean scope(int r,int c){
        return r>=0 && r<=100 && c>=0 && c<=100;
    }
}