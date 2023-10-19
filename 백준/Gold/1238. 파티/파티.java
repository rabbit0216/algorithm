import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X;
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] revGraph;
    static PriorityQueue<Node> pq;

    static class Node {

        int end;
        int dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        revGraph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, dist));
            revGraph[end].add(new Node(start, dist));
        }

        pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.dist, o2.dist);
            }
        });

        int[] dist = dijkstra(graph);
        int[] revDist = dijkstra(revGraph);

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            ans = Math.max(dist[i] + revDist[i], ans);
        }

        System.out.println(ans);
    }

    static boolean[] visited;

    private static int[] dijkstra(ArrayList<Node>[] targetGraph) {
        visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.clear();
        pq.offer(new Node(X, 0));
        dist[X] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.end]) {
                continue;
            }
            visited[cur.end] = true;

            for (Node next : targetGraph[cur.end]) {
                if (dist[next.end] > dist[cur.end] + next.dist) {
                    dist[next.end] = dist[cur.end] + next.dist;
                    next.dist = dist[next.end];
                    pq.offer(next);
                }
            }
        }
        return dist;
    }

}