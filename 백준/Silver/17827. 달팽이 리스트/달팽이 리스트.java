import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N,M,V;
    static int[] vertex;


    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); V = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        vertex = new int[N+1];
        for (int i = 1; i <= N; i++) {
            vertex[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int idx = Integer.parseInt(br.readLine());
            if(idx < N){
                sb.append(vertex[idx + 1]);
            } else {
                idx %= N;
                int target = idx + V;
                if(target > N) {
                    target = N;
                }
                sb.append(vertex[target]);
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush(); bw.close();

    }
}