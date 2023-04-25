import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * 나눗셈 구현
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int ans = 0;
        int tmpA = A;
        for (int i = 0; i < N; i++) {
            int rem = tmpA % B;
            rem *= 10;
            tmpA = rem;
            ans = tmpA / B;
        }
        System.out.println(ans);

    }
}