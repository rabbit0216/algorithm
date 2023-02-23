import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C;
    static char[][] board;
    static char[][] alphabet = {{'A', 0}, {'B', 0}, {'C', 0}, {'D', 0}, {'E', 0}, {'F', 0}, {'G', 0}, {'H', 0}, {'I', 0}, {'J', 0}, {'K', 0}, {'L', 0}, {'M', 0}, {'N', 0}, {'O', 0},
            {'P', 0}, {'Q', 0}, {'R', 0}, {'S', 0}, {'T', 0}, {'U', 0}, {'V', 0}, {'W', 0}, {'X', 0}, {'Y', 0}, {'Z', 0}};
    static boolean[] visited;
    static Queue<Node> queue;
    static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    static int[][] dist;
    static int ans;

    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

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
        dist = new int[R][C];
        dist[0][0] = 1;
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
            dist[nr][nc] = dist[r][c] + 1;
            solution(nr,nc, cnt + 1);
            visited[board[nr][nc]-65] = false;
        }
        ans = Math.max(ans, cnt);
    }
    static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}