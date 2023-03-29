import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] adj;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        adj = new int[N][N];
        visited = new boolean[N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                adj[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int target = 0; target < N; target++) {
            travel(target, target, 0, 0);
        }
        System.out.println(minAns);
    }

    static int minAns = Integer.MAX_VALUE;

    private static void travel(int city, int target, int dist, int visitedCity) {
        if (visitedCity == N && city == target) {
            minAns = Math.min(minAns, dist);
            return;
        }
        if (!visited[target]) {
            visited[target] = true;
            for (int next = 0; next < N; next++) {
                if (adj[target][next] != 0) {
                    travel(city, next, adj[target][next] + dist, visitedCity + 1);
                }
            }
            visited[target] = false;
        }
    }
}