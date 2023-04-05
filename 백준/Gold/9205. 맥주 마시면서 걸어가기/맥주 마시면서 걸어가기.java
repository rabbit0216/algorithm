import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * #floyd-warshall
     * 1. start - v - end 존재하면 happy!
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static ArrayList<Node> points;
    static Node start, end;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            points = new ArrayList<>();

            for (int i = 0; i < N+2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                Node cur = new Node(x,y);
                if(i == 0) start = cur;
                else if(i == N+1) end = cur;
                points.add(cur);
            }

            int[][] dp = new int[points.size()][points.size()];
            int INF = 1_000_000_000;
            for (int r = 0; r < points.size(); r++) {
                for (int c = 0; c < points.size(); c++) {
                    if(r == c) dp[r][c] = 0;
                    else{
                        int dist = Math.abs(points.get(r).x - points.get(c).x) +  Math.abs(points.get(r).y - points.get(c).y);
                        if(dist > 1000) dp[r][c] = INF;
                        else dp[r][c] = 1;
                    }
                }
            }

            for (int v = 0; v < points.size(); v++) {
                for (int s = 0; s < points.size(); s++) {
                    for (int e = 0; e < points.size(); e++) {
                        // 거리 : s - v + v - e, s - v && v - e 갈 때 1000보다 작거나 같
                        dp[s][e] = Math.min(dp[s][e], dp[s][v] + dp[v][e]);
                    }
                }
            }

            if(dp[0][points.size()-1] != INF) sb.append("happy");
            else sb.append("sad");
            sb.append('\n');
        }
        System.out.println(sb);
    }


    static class Node implements Comparable<Node> {
        int x,y;
        int toStart = 0;
        int toEnd = 0;
        int dist;
        boolean isEnd = false;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }

    }
}