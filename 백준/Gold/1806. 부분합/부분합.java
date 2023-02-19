import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder output;
    static int N, S;
    static Integer[] arr;
    static int[] sumArr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        output = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new Integer[N];
        sumArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sumArr[0] = arr[0];
        int start = 0;
        int end = 1;
        int minLength = Integer.MAX_VALUE;
        int length = 1;
        while(start < N){
            if(start == end) ++end;
            if(sumArr[end - 1] >= S) {
                minLength = Math.min(length,minLength);
                sumArr[end - 1] -= arr[start++];
                length--;
                continue;
            }
            if(end == N) {
                break;
            }
            sumArr[end] = sumArr[end - 1] + arr[end];
            end++;
            length++;
        }
        if (minLength == Integer.MAX_VALUE) minLength = 0;
        output.append(minLength);
        System.out.println(output);
    }
}