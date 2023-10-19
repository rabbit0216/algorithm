import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] fluid;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        fluid = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            fluid[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        String answer = "";
        int left = 0;
        int right = N-1;
        while(left < right) {
            int sum = fluid[left] + fluid[right];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                answer = fluid[left] + " " + fluid[right];
            }
            if(sum > 0) {
                right -= 1;
            } else {
                left += 1;
            }
        }

        System.out.println(answer);
    }

}