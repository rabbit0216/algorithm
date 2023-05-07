import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] graph;
    static boolean[][] visited;
    static PriorityQueue<Plant> pq;
    static int N;
    static StringBuilder ansCommand = new StringBuilder();
    static int ansSum = 0;
    static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static Plant plant;

    static class Plant {
        Node leftUp;
        Node rightDown;
        StringBuilder sb;
        int sum;
        int rsize, csize;

        public Plant(Node leftUp, Node rightDown, int sum, int rsize, int csize) {
            this.leftUp = leftUp;
            this.rightDown = rightDown;
            this.sb = new StringBuilder();
            this.sum = sum;
            this.rsize = rsize;
            this.csize = csize;
        }

        @Override
        public String toString() {
            return "Plant{" +
                    "leftUp=" + leftUp +
                    ", rightDown=" + rightDown +
                    ", sb=" + sb +
                    ", sum=" + sum +
                    ", rsize=" + rsize +
                    ", csize=" + csize +
                    '}';
        }
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        plant = new Plant(new Node(N / 2 - 1, N / 2 - 1), new Node(N / 2, N / 2 ),
                sum, 2, 2);
        calc();
        bw.write(ansSum + "" + '\n' + ansCommand.toString());
        bw.flush(); bw.close();
    }

    private static void calc() {
        while (true) {
            int maxSum = Integer.MIN_VALUE;
            int maxCommand = 0;
            for (int dir = 0; dir < deltas.length; dir++) {
                int sum = 0;
                if (dir >= 2) {
                    int rsize = plant.rsize;
                    int r = plant.leftUp.r;
                    if (dir == 3) {
                        int c = plant.rightDown.c;
                        if (c + 1 < N) {
                            for (int i = 0; i < rsize; i++) {
                                sum += graph[r + i][c + 1];
                            }
                        }
                    } else {
                        int c = plant.leftUp.c;
                        if (c - 1 >= 0) {
                            for (int i = 0; i < rsize; i++) {
                                sum += graph[r + i][c - 1];
                            }
                        }
                    }
                } else {
                    int csize = plant.csize;
                    int c = plant.leftUp.c;
                    if (dir == 1) {
                        int r = plant.rightDown.r;
                        if (r + 1 < N) {
                            for (int i = 0; i < csize; i++) {
                                sum += graph[r + 1][c + i];
                            }
                        }
                    } else {
                        int r = plant.leftUp.r;
                        if (r - 1 >= 0) {
                            for (int i = 0; i < csize; i++) {
                                sum += graph[r - 1][c + i];
                            }
                        }
                    }
                }
                if (maxSum < sum) {
                    maxSum = sum;
                    maxCommand = dir;
                }
            }
            if(maxSum <= 0) break; // 먹을 수 있는 양분 x

            char comm;
            if (maxCommand == 0) { // 상
                comm = 'U';
                plant.leftUp.r = plant.leftUp.r - 1;
                plant.rsize++;
            } else if (maxCommand == 1) { // 하
                comm = 'D';
                plant.rightDown.r = plant.rightDown.r + 1;
                plant.rsize++;
            } else if (maxCommand == 2) { // 좌
                comm = 'L';
                plant.leftUp.c = plant.leftUp.c - 1;
                plant.csize++;
            } else { // 우
                comm = 'R';
                plant.rightDown.c = plant.rightDown.c + 1;
                plant.csize++;
            }
            ansSum += maxSum;
            ansCommand.append(comm);
        }
    }
}