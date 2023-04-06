import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * #union-find
     * 1. 입력 받을 때 마다 union
     * 2. union 할 때, 몇번째인지 저장
     *  2-1. makeSet()?
     *  2-2. 경로 압축 x ?
     *
     * @param args
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ;
    static StringTokenizer st;
    static int N;
    static int[] repres;
    static int[] org;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());
            repres = new int[N+1];
            org = new int[N+1];

            for (int i = 1; i <= N ; i++) {
                repres[i] = i; // 자기 자신으로 초기화
            }

            st = new StringTokenizer(br.readLine());
            int ans = N;

            visited = new boolean[N+1];
            for (int i = 1; i <= N; i++) {
                int target = Integer.parseInt(st.nextToken());
                org[i] = target;
                if(!union(i, target)) { // 사이클 형성 되어있으면
                    int cnt = 0;
                    ans -= dfs(org[i], cnt);

                }
            }

            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    private static int dfs(int vertex, int cnt) {
        if(visited[vertex]) {
            return cnt;
        }
        visited[vertex] = true;
        return dfs(org[vertex], cnt+1);
    }

    private static int findSet(int v){
        if(v == repres[v]) return v;
        return findSet(repres[v]);
    }

    private static boolean union(int a,int b){
        int rootA = findSet(a);
        int rootB = findSet(b);

        if(rootA == rootB) {
            return false;
        }
        else {
            repres[rootA] = rootB;
            return true;
        }
    }

}