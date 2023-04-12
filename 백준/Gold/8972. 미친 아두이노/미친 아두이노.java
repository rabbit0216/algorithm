import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /**
     * #시뮬레이션
     *
     * 1. 종수 아두이노 커맨드대로 이동
     * 2. 맨해튼거리 제일 작아지는 위치로 미친 아두이노 이동
     * 3. 미친 아두이노 같은 칸에 2개 이상 -> 같이 있는 아두이노 파괴 : arraylist[][]
     * 4. 미친 아두이노와 종수 위치 같으면 게임 끝 "kraj "
     * @param args
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R,C;
    static ArrayList<Node>[][] graph;
    static char[][] orgGraph;
    static boolean[][] visited;
    static Node jongsoo;
    static ArrayList<Node> crazy;
    static final int[][] deltas = {{},{-1,1},{0,1},{1,1},{-1,0},{0,0},{1,0},{-1,-1},{0,-1},{1,-1}};
    static String command;
    static boolean isEnd;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        graph = new ArrayList[R][C];
        orgGraph = new char[R][C];
        visited = new boolean[R][C];
        crazy = new ArrayList<>();
        isEnd = false;

        int crazyIdx = 0;
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                graph[r][c] = new ArrayList<>();
                orgGraph[r][c] = s.charAt(c);

                if(orgGraph[r][c] == 'I'){
                    jongsoo = new Node(r,c,0);
                } else if(orgGraph[r][c] == 'R'){
                    Node node = new Node(r,c,crazyIdx++);
                    crazy.add(node);
                    graph[r][c].add(node);
                }
            }
        }

        // 입력받은 커맨드 사이즈 만큼 게임 진행
        command = br.readLine();
        for (int i = 0; i < command.length(); i++) {
            int dir = command.charAt(i) - '0';
            // 종수 이동
            orgGraph[jongsoo.r][jongsoo.c] = '.';
            int r = jongsoo.r + deltas[dir][1];
            int c = jongsoo.c + deltas[dir][0];
            jongsoo.r = r; jongsoo.c = c;
            orgGraph[r][c] = 'I';
            
            // 미친 아두이노들과 종수 거리 계산
            calcDist();

            // 아두이노 폭파 및 검사
            bomb();
            if(isEnd) {
                System.out.println("kraj " + (i+1));
                return ;
            }
        }

        print();
    }

    private static void print(){
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(orgGraph[r][c]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void bomb(){
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(graph[r][c].size() >= 1){
                    if(r == jongsoo.r && c == jongsoo.c){
                        isEnd = true;
                        return ;
                    }
                }

                if(graph[r][c].size() > 1){
                    for (int i = 0; i < graph[r][c].size(); i++) {
                        Node cur = graph[r][c].get(i);
                        for (int j = 0; j < crazy.size(); j++) {
                            Node toRemove = crazy.get(j);
                            if(cur.idx == toRemove.idx){
                                crazy.remove(toRemove);
                                break;
                            }
                        }
                    }
                    graph[r][c].clear();
                    orgGraph[r][c] = '.';
                } else if(graph[r][c].size() == 1){
                    orgGraph[r][c] = 'R';
                }
            }
        }
    }

    private static void calcDist(){
        for (int i = 0; i < crazy.size(); i++) {
            Node cur = crazy.get(i);
            graph[cur.r][cur.c].remove(cur);
            orgGraph[cur.r][cur.c] = '.';
            // 8방향으로 가보고 제일 짧은 거리로 이동하기
            int minDist = Integer.MAX_VALUE;
            int minR = cur.r;
            int minC = cur.c;
            for (int dir = 1; dir < deltas.length; dir++) {
                int nr = cur.r + deltas[dir][1];
                int nc = cur.c + deltas[dir][0];
                if(!scope(nr,nc)) continue;
                int dist = Math.abs(jongsoo.r - nr) + Math.abs(jongsoo.c - nc);
                if(minDist > dist){
                    minDist = dist;
                    minR = nr;
                    minC = nc;
                }
            }
            cur.r = minR; cur.c = minC;
            graph[minR][minC].add(cur);
            orgGraph[minR][minC] = 'R';
        }
    }

    private static boolean scope(int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
    private static class Node{
        int r,c;
        int idx;

        public Node(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", idx=" + idx +
                    '}';
        }
    }
}