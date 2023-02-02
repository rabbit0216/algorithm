import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] house = new int[N+1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            house[i][0] = r;
            house[i][1] = g;
            house[i][2] = b;
        }

        for(int i=2; i<=N; i++){
            //red
            house[i][0] += Math.min(house[i-1][1], house[i-1][2]);
            //green
            house[i][1] += Math.min(house[i-1][0], house[i-1][2]);
            //blue
            house[i][2] += Math.min(house[i-1][0], house[i-1][1]);
        }
        int ans = Math.min(Math.min(house[N][0], house[N][1]),house[N][2]);
        System.out.println(ans);
    }
}
