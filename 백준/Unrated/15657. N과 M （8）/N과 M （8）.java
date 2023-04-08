import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        make(0,new int[M],0);
        System.out.println(sb);
    }

    private static void make(int nthChoice, int[] choice, int startIdx){
        if(nthChoice == choice.length){
            for (int i = 0; i < choice.length; i++) {
                sb.append(choice[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = startIdx; i < arr.length; i++) {
            choice[nthChoice] = arr[i];
            make(nthChoice+1, choice, i);
        }
    }
}