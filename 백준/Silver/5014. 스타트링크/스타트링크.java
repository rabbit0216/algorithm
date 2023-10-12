import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int F, S, G, U, D;
    static String ans;
    static ArrayDeque<Node> queue;
    static boolean[] visited;

    static class Node {

        int x;
        int dist;

        public Node(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        queue = new ArrayDeque<>();
        visited = new boolean[F + 1];
        ans = "use the stairs";

        queue.offer(new Node(S, 0));
        visited[S] = true;
        bfs();

        bw.write(ans);
        bw.flush();
        bw.close();
    }

    static private void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.x == G) {
                ans = cur.dist + "";
                return;
            }

            int up = cur.x + U;
            int down = cur.x - D;

            if (up <= F && !visited[up]) {
                visited[up] = true;
                queue.offer(new Node(up, cur.dist + 1));
            }

            if (down >= 1 && !visited[down]) {
                visited[down] = true;
                queue.offer(new Node(down, cur.dist + 1));
            }
        }
    }

}