import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static ArrayList<Integer>[] graph;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C][3];

        graph = new ArrayList[R];
        for (int i = 0; i < R; i++) {
            graph[i] = new ArrayList();
        }

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            int before = -1;
            for (int c = 0; c < C; c++) {
                int cur = Integer.parseInt(st.nextToken());
                if (before != cur) {
                    graph[r].add(cur);
                    before = cur;
                }
            }
        }

        int ans = 0;

        for (int r = 0; r < R; r++) {
            if (graph[r].get(0) == 0 && graph[r].size() == 1) continue;
            int[] cnt = new int[2];
            if (graph[r].get(0) != 0) {
                cnt[0] = 1; cnt[1] = 1;
            }
            boolean endZero = false;
            for (int c = 0; c < graph[r].size(); c++) {
                // 0 기준으로 초기화
                if (graph[r].get(c) == 0) {
                    ans += Math.min(cnt[0], cnt[1]);
                    cnt[0] = 1; cnt[1] = 1;
                    endZero = true;
                    continue;
                }

                // 1
                if (graph[r].get(c) == 2) cnt[0]++;

                // 2
                if (graph[r].get(c) == 1) cnt[1]++;
                endZero = false;
            }
            if(!endZero) {
                ans += Math.min(cnt[0], cnt[1]);
            }

        }
        System.out.println(ans);
    }

}