import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] map;
    static ArrayList<Node> chickens;
    static ArrayList<Node> house;
    static final int HOUSE = 1, CHICKEN = 2;
    static int minDist = Integer.MAX_VALUE;

    static class Node {

        int x, y;
        int dist;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.dist = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chickens = new ArrayList<>();
        house = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == CHICKEN) {
                    chickens.add(new Node(r, c));
                } else if (map[r][c] == HOUSE) {
                    house.add(new Node(r, c));
                }
            }
        }

        makeCombi(0, new int[M], 0);
        bw.write(minDist + "");
        bw.flush();
        bw.close();

    }

    private static void makeCombi(int nthChoice, int[] choosed, int startIdx) {
        if (nthChoice == choosed.length) {
            // 집들이랑 각각 거리 계산
            int dist = 0;
            for (Node h : house) {
                h.dist = Integer.MAX_VALUE;
            }

            for (int i = 0; i < choosed.length; i++) {
                int chickenIdx = choosed[i];

                Node chicken = chickens.get(chickenIdx);
                for (Node h : house) {
                    int calc = Math.abs(chicken.x - h.x) + Math.abs(chicken.y - h.y);
                    h.dist = Math.min(h.dist, calc);
                }
            }

            for (Node h : house) {
                dist += h.dist;
            }

            minDist = Math.min(minDist, dist);
            return;
        }

        for (int i = startIdx; i < chickens.size(); i++) {
            choosed[nthChoice] = i;
            makeCombi(nthChoice + 1, choosed, i + 1);
        }
    }
}