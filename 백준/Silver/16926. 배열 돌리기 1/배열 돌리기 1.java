import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C, rotate;
    static int[][] arr;
    static int[][] tmpArr;
    static boolean[][] visited;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        output = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        rotate = Integer.parseInt(st.nextToken());
        arr = new int[R + 1][C + 1];
        tmpArr = new int[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int depth = Math.min(R, C) / 2;
        while (rotate > 0) {
            visited = new boolean[R + 1][C + 1];
            for (int d = 0; d < depth; d++) {
                rotateArr(1 + d, 1 + d);
            }
            copyArr(tmpArr);
            rotate--;
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                output.append(arr[r][c]).append(" ");
            }
            output.append('\n');
        }
        System.out.println(output);
    }


    private static void rotateArr(int r, int c) {
        final int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int nr = r;
        int nc = c;
        for (int dir = 0; dir < deltas.length; ) {
            int before = arr[nr][nc];
            nr += deltas[dir][1];
            nc += deltas[dir][0];
            if (!scope(nr, nc) || visited[nr][nc]) {
                nr -= deltas[dir][1];
                nc -= deltas[dir][0];
                dir++;
            } else {
                tmpArr[nr][nc] = before;
                visited[nr][nc] = true;
            }
        }
        tmpArr[r][c] = arr[r][c + 1];
    }

    private static boolean scope(int r, int c) {
        return r >= 1 && r <= R && c >= 1 && c <= C;
    }


    private static void copyArr(int[][] tmp) {
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                arr[r][c] = tmp[r][c];
            }
        }
    }
}