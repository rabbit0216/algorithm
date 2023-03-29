import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * #이분탐색
     * 
     * 1. 최소시간 : 제일 짧은 시간
     * 2. 최대시간 : 제일 긴 시간 * 인원 수
     * 3. 시간을 이분탐색 하면서, 중간 시간에 총 심사 가능한 인원수 구하기
     * 4. 인원수의 값에 따라 l, r값 갱신
     * 5. 총 심사 가능한 인원 수가 M보다 크면 답이 될 수 있음 => 최소값으로 갱신!
     */
    static long  M;
    static int N;
    static long[] infoTime;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Long.parseLong(st.nextToken());
        infoTime = new long[N];
        for(int i=0;i<N;i++){
            long time = Long.parseLong(br.readLine());
            infoTime[i] = time;
        }
        Arrays.sort(infoTime);
        long minTime = infoTime[0];
        long maxTime = infoTime[infoTime.length-1] * M;

        long ans = maxTime;
        while(maxTime >= minTime) {
            long totalTime = 0;
            long mid = (minTime + maxTime) / 2;
            for (int i = 0; i < N; i++) {
                // mid 시간 동안 몇명이 심사 가능한지 계산
                totalTime += mid / infoTime[i];
                if(totalTime > M) break; // 왜 이거 안쓰면 틀림??
            }
            if(totalTime < M){
                minTime = mid + 1;
            } else {
                maxTime = mid - 1;
                ans = Math.min(ans, mid);
            }
        }
        System.out.println(ans);
    }
}