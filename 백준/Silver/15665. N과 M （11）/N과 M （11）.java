import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder();
        Arrays.sort(nums);
        makePerm(0, new int[M]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void makePerm(int nthChoice, int[] choosed) {
        if (nthChoice == choosed.length) {
            for (int i = 0; i < choosed.length; i++) {
                sb.append(choosed[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int before = -1;
        for (int i = 0; i < nums.length; i++) {
            if (before != nums[i]) {
                before = nums[i];
                choosed[nthChoice] = nums[i];
                makePerm(nthChoice + 1, choosed);
            }
        }
    }
}