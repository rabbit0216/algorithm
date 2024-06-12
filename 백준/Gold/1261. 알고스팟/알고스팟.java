import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int X, Y;
    static int[][] maze;
    static boolean[][][] visited;
    static final int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static PriorityQueue<Node> queue;
    static int result;

    public static class Node {
        int x, y;
        int wall;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.wall = 0;
        }

        public Node(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", wall=" + wall +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        maze = new int[Y][X];
        visited = new boolean[Y][X][2];
        for (int y = 0; y < Y; y++) {
            String s = br.readLine();
            for (int x = 0; x < X; x++) {
                maze[y][x] = s.charAt(x) - '0';
            }
        }

        result = 0;
        queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.wall, o2.wall);
            }
        });
        queue.offer(new Node(0, 0));
        visited[0][0][0] = true;
        bfs();

        bw.write(result + "");
        bw.flush();
        bw.close();

    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == X - 1 && cur.y == Y - 1) {
                result = cur.wall;
                return;
            }
            for (int dir = 0; dir < deltas.length; dir++) {
                int nx = cur.x + deltas[dir][0];
                int ny = cur.y + deltas[dir][1];

                if (!isIn(nx, ny)) continue;
                if (visited[ny][nx][0]) continue;

                if (maze[ny][nx] == 1 && !visited[ny][nx][1]) {
                    visited[ny][nx][1] = true;
                    queue.offer(new Node(nx, ny, cur.wall + 1));
                }

                if (maze[ny][nx] == 0) {
                    visited[ny][nx][0] = true;
                    queue.offer(new Node(nx, ny, cur.wall));
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }
}