import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static long[] liquid;
    static long minDiff;
    static long[] selectedLiquid;

    /**
     * #투포인터
     * 
     * [출력]
     * 절댓값이 가장 작은 용액
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        liquid = new long[N];
        selectedLiquid = new long[3];
        for (int i = 0; i < N; i++) {
            liquid[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liquid);
        minDiff = Long.MAX_VALUE;
        for(int fix=0;fix<N;fix++){
            long cur = liquid[fix];
            int start = 0;
            int end = N - 1;
            long sum = 0;
            while(start < end){
                if(fix == start) start++;
                else if(fix == end) end--;
                if(start == end) break;
                sum = cur + liquid[start] + liquid[end];
                long diff = Math.abs(sum);
                if(diff < minDiff){
                    // 절대값이 현재 나온 절대값보다 작으면 갱신, 해당 세트 저장
                    minDiff = diff;
                    selectedLiquid[0] = cur;
                    selectedLiquid[1] = liquid[start];
                    selectedLiquid[2] = liquid[end];
                }
                if(sum > 0){
                    end--;
                } else if(sum < 0) {
                    start++;
                } else { // 합이 0이면 최소이므로 끝
                    break;
                }
            }
        }

        Arrays.sort(selectedLiquid);
        for(long liquid : selectedLiquid){
            System.out.print(liquid + " ");
        }

    }
}