import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] toCalc;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        toCalc = new int[2];
        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        toCalc[0] = Integer.parseInt(st.nextToken());
        toCalc[1] = Integer.parseInt(st.nextToken());

        int relationNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < relationNum; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            adj[parent].add(child);
            adj[child].add(parent);
        }

        int ans = dfs(toCalc[0], toCalc[1], 0);
        System.out.println(ans);
    }

    static int dfs(int start, int end, int depth) {
        if (start == end) {
            System.out.println(depth);
            System.exit(0);
        }
        if (visited[start]) return -1;
        visited[start] = true;

        for (int next : adj[start]) {
            dfs(next, end, depth + 1);
        }

        return -1;
    }
}