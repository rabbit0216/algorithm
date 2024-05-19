import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] bro = new int[N];
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            bro[i] = Integer.parseInt(st.nextToken());
            diff[i] = Math.abs(bro[i] - S);
        }

        int gcd = diff[0];
        for (int i = 1; i < N; i++) {
            gcd = getGCD(gcd, diff[i]);
        }
        System.out.println(gcd);

    }

    private static int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}