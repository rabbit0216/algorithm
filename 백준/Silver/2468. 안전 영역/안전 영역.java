import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    /**
     * #dfs #bfs
     * <p>
     * 1. 모든 영역의 높이 저장 (set or boolean 배열)
     * 2. 모든 높이로 안전 영역 개수 카운팅
     * 2-1. dfs / bfs 로 상하좌우 인접한 면이 잠겨있지 않으면 탐색 계속, 잠겨있으면 끝, 안전영역 개수 +1
     * 2-2. dfs 경우, 처음 재귀 끝나면 +1 해야됨
     * 3. 최대 개수 답
     *
     * @param args
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[][] graph;
    static boolean[][] visited;
    static int[] num;
    static Set<Integer> nums;
    static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        nums = new HashSet<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                nums.add(graph[r][c]);
            }
        }

        int ans = 1;
        Iterator<Integer> iter = nums.iterator();
        while (iter.hasNext()) {
            int h = iter.next();
            int cnt = 0;

            // 그래프 상 해당하는 높이 이하 다 visited 처리
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (graph[r][c] <= h) {
                        visited[r][c] = true;
                    }
                }
            }

            // dfs
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (visited[r][c]) continue;
                    dfs(r, c);
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }
        bw.write(ans+"");
        bw.flush(); bw.close();
    }

    static void dfs(int r,int c){
        if(visited[r][c]) return;
        visited[r][c] = true;

        for (int i = 0; i < deltas.length; i++) {
            int nr = r + deltas[i][1];
            int nc = c + deltas[i][0];
            if(!scope(nr,nc)) continue;
            dfs(nr,nc);
        }
    }

    static boolean scope(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }
}