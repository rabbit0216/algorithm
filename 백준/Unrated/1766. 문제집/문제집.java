import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    /**
     * #위상정렬
     * 1. pq, indegree 0인 애들 넣기
     * @param args
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[] indegree;
    static PriorityQueue<Integer> pq;
    static ArrayList<Integer>[] adj;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        pq = new PriorityQueue<>();
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); int to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            indegree[to]++;
        }

        for (int i = 1; i <= N ; i++) { // 들어오는 간선 0인 노드 넣기
            if(indegree[i] == 0){
                pq.offer(i);
            }
        }

        topologySort();
        System.out.println(sb);
    }

    private static void topologySort(){
        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(' ');

            for (int next : adj[cur]) {
                if(--indegree[next] == 0){
                    pq.offer(next);
                }
            }
        }
    }
}