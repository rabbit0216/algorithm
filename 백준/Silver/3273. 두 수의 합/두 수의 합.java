import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int X = Integer.parseInt(br.readLine());

        Arrays.sort(nums);

        int l = 0; int r = N-1;
        int ans = 0;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if(sum == X) {
                l++; r--;
                ans++;
            } else if(sum < X) {
                l++;
            } else {
                r--;
            }
        }

        bw.write(ans+""); bw.flush(); bw.close();
    }
}