import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static HashSet<Long> numberSet = new HashSet<>();
    static int N;
    static long a, d;
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        if(d != 1) {
            calcDiv();
            Iterator<Long> iter = numberSet.iterator();
            while (iter.hasNext()) {
                long num = iter.next();
                if (d % num == 0) {
                    sb.append("No");
                    bw.write(sb.toString());
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }



        sb.append("Yes").append('\n');
        long[] arr = new long[N*2];
        arr[0] = a;
        for (int i = 1; i < N * 2; i++) {
            arr[i] = arr[i-1] + d;
        }

        for (int i = 0; i < N * 2; i+=2) {
            sb.append(arr[i]).append(" ").append(arr[i+1]).append('\n');
        }
        bw.write(sb.toString()); bw.flush(); bw.close();
    }

    static void calcDiv(){
        for (long i = 1; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {

                numberSet.add(a / i);
                if(i != 1) {
                    numberSet.add(i);
                }
            }
        }
    }
}
