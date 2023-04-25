import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static int[][] graph;
    static final int[][] deltas = {{0,-1},{1,0},{0,1},{-1,0}}; // 북 동 남 서
    static Node robot;
    static int ans;

    static class Node {
        int r,c;
        int dir;

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new int[R][C];
        ans = 0;

        st = new StringTokenizer(br.readLine());
        robot = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        clean();
        System.out.println(ans);
    }

    private static void clean() {
        while(true){
            // 현재 칸 청소 (후진 할 때, 청소한 칸인지 확인 안하고 무조건 + 시켜서 답보다 더 많이 출력됨)
            if(graph[robot.r][robot.c] != -1) {
                graph[robot.r][robot.c] = -1;
                ans++;
            }

//            for (int[] arr: graph) {
//                System.out.println(Arrays.toString(arr));
//            }
//            System.out.println("방향 : " + robot.dir + " ans : " + ans + "\n========================");

            boolean canClean = false;
            // 주변 4칸 확인
            for (int dir = 0; dir < deltas.length; dir++) {
                int nr = robot.r + deltas[dir][1];
                int nc = robot.c + deltas[dir][0];

                if(!scope(nr,nc)) continue;
                if(graph[nr][nc] == 1) continue;
                else if(graph[nr][nc] == 0) {
                    canClean = true;
                    break;
                }
            }

            // 청소 할 수 있는 칸이 없음
            if(!canClean){
                // 한칸 후진
                int backDir = (robot.dir + 2) % 4;
                int nr = robot.r + deltas[backDir][1];
                int nc = robot.c + deltas[backDir][0];

                if(scope(nr,nc)) {
                    if(graph[nr][nc] == 1) { // 후진했는데 벽 --> 종료!
                        break;
                    } else { // 아니면 후진
                        robot.r = nr;
                        robot.c = nc;
                    }
                } else {
                    break;
                }
            } else { // 청소 가능
                int i=0;
                while(true) {
                    robot.dir = ((robot.dir - 1) + 4) % 4; // 방향 회전
                    int nr = robot.r + deltas[robot.dir][1];
                    int nc = robot.c + deltas[robot.dir][0];
                    if (scope(nr, nc)) {
                        if (graph[nr][nc] == 0) {
                            robot.r = nr;
                            robot.c = nc;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}
