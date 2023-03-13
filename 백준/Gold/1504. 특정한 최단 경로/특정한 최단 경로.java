import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, E;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq;
    static int[] dist;
    static boolean[] visited;
    static int v1, v2;
    static int minAns;
    static int INF = Integer.MAX_VALUE;
    static boolean fail;

    static class Node{
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Node(to,weight));
            adj[to].add(new Node(from,weight));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()); v2 = Integer.parseInt(st.nextToken());

        /**
         * 1. 1 ~ v1 최단거리
         * 1-1. 1에서 v2 방문 안했다면, v1 ~ v2 최단거리
         * 2. v2 ~ N || v1 ~ N 최단거리
         * 3. v2 도 1~2 반복
         * 4. v1 버전과 v2 버전 중 거리가 더 짧은 거로 저장
         */

        pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.dist, o2.dist);
            }
        });
        dist = new int[N+1];
        minAns = INF;

        minAns = Math.min(dijkstra(1,v1) + dijkstra(v1, v2) + dijkstra(v2, N), // 1 - v1 - v2 - N
                dijkstra(1,v2) + dijkstra(v2, v1) + dijkstra(v1, N)); // 1 - v2 - v1 - N

        if(fail) System.out.println(-1);
        else System.out.println(minAns);
    }

    private static int dijkstra(int start, int end) {
        pq.clear();
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start,dist[start]));
        visited = new boolean[N+1];
        while(true){
            Node cur = pq.poll();
            if(cur == null) {
                fail = true;
                return INF;
            }
            if(visited[cur.v]) continue;
            visited[cur.v] = true;
            if(cur.v == end) return cur.dist;
            int minCost = cur.dist;

            for(Node next : adj[cur.v]){
                if(dist[next.v] > minCost + next.dist){
                    dist[next.v] = minCost + next.dist;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}