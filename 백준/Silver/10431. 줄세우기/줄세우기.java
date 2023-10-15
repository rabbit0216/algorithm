import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T;
    static int[] students;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            students = new int[20];
            for (int i = 0; i < 20; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            for (int cur = 1; cur < 20; cur++) {
                boolean change = false;
                int frontCnt = 0;
                for (int prev = cur - 1; prev >= 0; prev--) {
                    if (students[prev] > students[cur]) { // 앞사람이 자기보다 크면
                        change = true;
                        frontCnt++;
                    }
                }
                if (change) {
                    answer += frontCnt;
                }
            }
            sb.append(test_case).append(" ").append(answer).append('\n');
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}