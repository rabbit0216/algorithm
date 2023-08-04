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
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] temperature = new int[N];
        for (int i = 0; i < N; i++) {
            temperature[i] = Integer.parseInt(st.nextToken());
        }

        int maxAns = Integer.MIN_VALUE;
        for (int i = 0; i <= N - K; i++) {
            int sum = 0;
            for (int j = i; j < i + K; j++) {
                sum += temperature[j];
            }
            maxAns = Math.max(sum, maxAns);
        }

        bw.write(maxAns + "");
        bw.flush();
        bw.close();
    }
}