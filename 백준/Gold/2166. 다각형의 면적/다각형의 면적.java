import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static double[][] points;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        points = new double[N][2];
        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }

        double det = 0L;
        for (int i = 0; i < N - 1; i++) {
            det += points[i+1][1] * points[i][0];
            det -= points[i+1][0] * points[i][1];
        }

        det += points[N - 1][0] * points[0][1];
        det -= points[N-1][1] * points[0][0];
        det = Math.abs(det);
        double ans = det / 2;
        System.out.printf("%.1f", ans);

    }

}