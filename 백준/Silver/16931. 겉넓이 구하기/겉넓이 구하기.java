import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] cube = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                cube[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int result = R * C * 2;
        int side = 0;

        for (int r = 0; r < R; r++) {
            side += cube[r][0];
            for (int c = 0; c < C; c++) {
                if (c != 0 && cube[r][c] > cube[r][c - 1]) {
                    side += cube[r][c] - cube[r][c - 1];
                }
            }
        }


        for (int c = 0; c < C; c++) {
            side += cube[0][c];
            for (int r = 0; r < R; r++) {
                if (r != 0 && cube[r][c] > cube[r - 1][c]) {
                    side += cube[r][c] - cube[r - 1][c];
                }
            }
        }

        result += side * 2;
        bw.write(result+""); bw.flush(); bw.close();
    }
}