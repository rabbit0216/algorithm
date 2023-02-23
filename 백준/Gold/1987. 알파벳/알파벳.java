import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C;
    static char[][] board;
    static boolean[] visited;
    static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    static int ans;

    /**
     * A : 65
     * Z : 90
     * -65 로 인덱싱
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[26];
        for(int r=0;r<R;r++){
            String s = br.readLine();
            for(int c=0;c<C;c++){
                board[r][c] = s.charAt(c);
            }
        }
        ans = 1;
        visited[board[0][0]-65] = true;
        solution(0,0, 1);
        System.out.println(ans);
    }

    static void solution(int r, int c, int cnt){
        for(int dir = 0; dir<4; dir++){
            int nr = r + deltas[dir][1];
            int nc = c + deltas[dir][0];
            if(!scope(nr,nc)) continue;
            if(visited[board[nr][nc]-65]) continue;
            visited[board[nr][nc]-65] = true;
            solution(nr,nc, cnt + 1);
            visited[board[nr][nc]-65] = false;
        }
        ans = Math.max(ans, cnt);
    }
    static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}