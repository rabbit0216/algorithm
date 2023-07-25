import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long total = Long.parseLong(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st ;

        long ans = 0L;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long price = Long.parseLong(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            ans += price * cnt;
        }

        if(total == ans) {
            bw.write("Yes");
        } else {
            bw.write("No");
        }

        bw.flush(); bw.close();
    }
}