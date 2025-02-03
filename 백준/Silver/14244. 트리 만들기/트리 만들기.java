import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        int leaf = 0;
        if(m == 2) leaf = 1;
        int beforeNode = 0;
        for (int i = 1; i < n; i++) {
            if(leaf < m) {
                sb.append(0).append(' ').append(i);
                leaf++;
            } else {
                sb.append(beforeNode).append(' ').append(i);
            }
            beforeNode = i;
            sb.append('\n');
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}
