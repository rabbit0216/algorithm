import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int H = Integer.parseInt(br.readLine());
        int W = Integer.parseInt(br.readLine());
        double r = Math.min(H, W);
        double ans = (r / 2) * 100;

        bw.write(String.format("%.0f", ans));
        bw.flush();
        bw.close();
    }
}