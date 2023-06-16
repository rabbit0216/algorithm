import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] dp;
    static boolean[] visited;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            tree[n].add(i);
        }

        for (int i = 1; i <= N ; i++) {
            dp[i] = 1;
        }

        dfs(1);

        Arrays.sort(dp);

        for (int i = 1; i <= N ; i++) {
            dp[i] -= 1;
        }

        int[] sum = new int[N+1];
        sum[0] = 0;
        sum[1] = dp[1];

        for (int i = 2; i <= N; i++) {
            sum[i] = sum[i-1] + dp[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= N; i++) {
            sb.append(sum[i]).append(" ");
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }

    static int dfs(int cur) {
        if (visited[cur]) return dp[cur];
        visited[cur] = true;

        for (int child : tree[cur]) {
            if(visited[child]) continue;
            dp[cur] = dp[cur] + dfs(child);
        }
        return dp[cur];
    }
}
