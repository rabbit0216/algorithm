import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new  StringBuilder();
    static int N, M;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq;

    static boolean[] visited;

    static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        for (Node node: adj[start]
             ) {
            pq.offer(node);
        }

        int ans = 0;
        visited = new boolean[N+1];

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.v == end) {
                ans = cur.cost;
                break;
            }
            if(visited[cur.v]){
                continue;
            }
            visited[cur.v] = true;

            for (Node next: adj[cur.v]
                 ) {
                pq.offer(new Node(next.v, next.cost + cur.cost));
            }
        }

        System.out.println(ans);
    }


}