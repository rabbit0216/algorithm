import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static String[] title;
    static int[] titleAtk;
    static int[] atk;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        title = new String[N];
        titleAtk = new int[N];
        atk = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            titleAtk[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            atk[i] = Integer.parseInt(br.readLine());

            int left = 0;
            int right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;

                if (atk[i] <= titleAtk[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            sb.append(title[left]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}