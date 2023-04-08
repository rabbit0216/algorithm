import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * 1. for 문으로 5가지 경우 체크, 최대 합 출력
     */

//    static final int[][][] tetromino = {
//            {{1, 0}, {2, 0}, {3, 0}}, {{0, 1}, {0, 2}, {0, 3}},
//            {{1, 0}, {0, 1}, {1, 1}},
//            {{0, 1}, {0, 2}, {1, 2}}, {{0, 1}, {0, 2}, {-1, 2}}, {{1, 0}, {0, 1}, {0, 2}}, {{1, 0}, {1, 1}, {1, 2}}, {{1, 0}, {2, 0}, {2, -1}}, {{0, 1}, {1, 1}, {2, 1}}, {{1, 0}, {2, 0}, {2, 1}}, {{0, 1}, {1, 0}, {2, 0}},
//            {{0, 1}, {1, 1}, {1, 2}}, {{0, 1}, {-1, 1}, {-1, 2}}, {{-1, 0}, {-1, 1}, {-2, 1}}, {{1, 0}, {1, 1}, {1, 2}},
//            {{1, 0}, {2, 0}, {1, 1}}, {{0, 1}, {1, 1}, {0, 2}}, {{0, 1}, {0, 2}, {-1, 1}}, {{-1, 1}, {0, 1}, {1, 1}}
//    };

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static int[][] graph;
    static final int[][][] tetromino = {{{0, 1}, {0, 2}, {0, 3}},
            {{1, 0}, {2, 0}, {3, 0}},
            {{0, 1}, {1, 0}, {1, 1}},
            {{1, 0}, {2, 0}, {2, 1}},
            {{1, 0}, {2, 0}, {2, -1}},
            {{1, 0}, {2, 0}, {0, 1}},
            {{1, 0}, {2, 0}, {0, -1}},
            {{0, 1}, {0, 2}, {1, 2}},
            {{0, 1}, {0, 2}, {-1, 2}},
            {{0, 1}, {0, 2}, {1, 0}},
            {{0, 1}, {0, 2}, {-1, 0}},
            {{0, 1}, {1, 1}, {1, 2}},
            {{0, 1}, {-1, 1}, {-1, 2}},
            {{1, 0}, {1, 1}, {2, 1}},
            {{1, 0}, {1, -1}, {2, -1}},
            {{0, 1}, {0, 2}, {1, 1}},
            {{1, 0}, {2, 0}, {1, -1}},
            {{0, 1}, {0, 2}, {-1, 1}},
            {{1, 0}, {2, 0}, {1, 1}}};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                for (int i = 0; i < 19; i++) { // 테트로미노 경우
                    int sum = graph[r][c];
                    for (int dir = 0; dir < 3; dir++) {
                        int nr = r + tetromino[i][dir][0];
                        int nc = c + tetromino[i][dir][1];
                        if (!scope(nr, nc)) {
                            sum = 0;
                            break;
                        }
                        sum += graph[nr][nc];
                    }
                    maxSum = Math.max(maxSum, sum);
                }
            }
        }

        System.out.println(maxSum);
    }

    static boolean scope(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

}