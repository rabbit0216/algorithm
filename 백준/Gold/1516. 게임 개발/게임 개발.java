import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] indegree;
    static int[] times;
    static int[] endTimes;
    static ArrayList<Integer>[] building;
    static PriorityQueue<Node> pq;

    static int N;

    static class Node {
        int v;
        int time;

        public Node(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        indegree = new int[N + 1];
        building = new ArrayList[N + 1];
        times = new int[N + 1];
        endTimes = new int[N+1];
        pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });

        for (int i = 0; i < N + 1; i++) {
            building[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                indegree[i]++;
                building[num].add(i);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                pq.offer(new Node(i, times[i]));
            }
        }
        topologySort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            sb.append(endTimes[i]).append("\n");
        }
        bw.write(sb.toString()); bw.flush(); bw.close();
    }

    private static void topologySort() {
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            endTimes[cur.v] = cur.time;

            for (int next : building[cur.v]) {
                if(--indegree[next] == 0){
                    pq.offer(new Node(next, cur.time + times[next]));
                }
            }
        }
    }
}