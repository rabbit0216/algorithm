import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        makePerm(0, new int[M], 0, new boolean[N]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void makePerm(int nthChoice, int[] choosed, int startIdx, boolean[] visited) {
        if (nthChoice == choosed.length) {
            for (int i = 0; i < choosed.length; i++) {
                sb.append(choosed[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int before = -1;
        for (int i = startIdx; i < nums.length; i++) {
            if (!visited[i] && before != nums[i]) {
                visited[i] = true;
                choosed[nthChoice] = nums[i];
                before = nums[i];
                makePerm(nthChoice + 1, choosed, i + 1, visited);
                visited[i] = false;
            }
        }
    }

}