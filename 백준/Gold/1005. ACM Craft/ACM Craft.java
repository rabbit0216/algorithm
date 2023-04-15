import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * #위상정렬
     * 1. 간선 0인 노드 큐에 넣기
     * 2. 간선 제거, 시간 갱신 (처음에 maxTime으로 고정시켜서 노드에 포함시켜서 진행 -> 배열 사용해서 갱신으로 변경)
     *
     * @param args
     */
    static int N, K;
    static BufferedReader br;
    static int[] buildTime;
    static ArrayList<Integer>[] adj;
    static int[] inDegree;
    static int target;
    static Queue<Integer> queue;
    static int[] maxTime;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            input();
            TopologySort();
            sb.append(maxTime[target]).append('\n');
        }
        System.out.println(sb);
    }

    static void TopologySort() {
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) { 
                queue.offer(i);
                maxTime[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == target) {
                return;
            }
            for (int next : adj[cur]) {
                if(maxTime[next] < maxTime[cur] + buildTime[next]){ // 최대 건설시간 갱신 (들어오는 간선 존재 할때도 계산 해줘야 함)
                    maxTime[next] = maxTime[cur] + buildTime[next];
                }
                if (--inDegree[next] == 0) {
                   queue.offer(next);
                }
            }
        }
    }

    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        buildTime = new int[N + 1];
        inDegree = new int[N + 1];
        maxTime = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            buildTime[i] = Integer.parseInt(st.nextToken());
        }
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            inDegree[to]++;
        }

        target = Integer.parseInt(br.readLine());
        queue = new ArrayDeque<>();
    }

}