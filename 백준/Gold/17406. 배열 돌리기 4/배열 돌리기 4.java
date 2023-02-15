import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C, rotate;
    static int[][] arr, initArr;

    static int[][] tmpArr;
    static boolean[][] visited;
    static int endR, endC, startR, startC, rotateR, rotateC, sub, minNum;
    static int[][] rotateSet;
    static int[] combi;

    public static void main(String[] args) throws IOException {
        // 각 행의 합 중 최솟값 출력
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        rotate = Integer.parseInt(st.nextToken());
        arr = new int[R + 1][C + 1];
        tmpArr = new int[R + 1][C + 1];
        rotateSet = new int[rotate][3];
        combi = new int[rotate];
        minNum = Integer.MAX_VALUE;
        initArr = new int[R+1][C+1];

        for (int i = 0; i < rotate; i++) {
            combi[i] = i;
        }

        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                initArr[r][c] = arr[r][c];
            }
        }
        
        // 회전 세트 저장
        for (int k = 0; k < rotate; k++) {
            st = new StringTokenizer(br.readLine());
            rotateSet[k][0] = Integer.parseInt(st.nextToken());
            rotateSet[k][1] = Integer.parseInt(st.nextToken());
            rotateSet[k][2] = Integer.parseInt(st.nextToken());
        }

        solution(0, new int[rotate], new boolean[rotate]);
        System.out.println(minNum);
    }

    private static void solution(int nthChoice, int[] choosed, boolean[] visitedChoice) {
        // 순열로 회전 순서 지정
        if (nthChoice == choosed.length) {
            // 처음 입력 받은 상태로 초기화
            arr = copyArr(initArr);
            // 회전 순서대로 회전
            for (int k = 0; k < rotate; k++) {
                rotateR = rotateSet[choosed[k]][0];
                rotateC = rotateSet[choosed[k]][1];
                sub = rotateSet[choosed[k]][2];
                // 돌릴 배열 시작지점과 끝지정 설정
                startR = rotateR - sub; startC = rotateC - sub;  endR = rotateR + sub; endC = rotateC + sub;
                int depth = Math.min(endR - startR + 1, endC - startC + 1) / 2;
                visited = new boolean[R + 1][C + 1];
                tmpArr = copyArr(arr);
                // 깊이만큼 돌리기
                for (int d = 0; d < depth; d++) {
                    rotateArr(startR + d, startC + d);
                }
                arr = copyArr(tmpArr);
            }
            
            // 최솟값 계산
            for (int r = 1; r <= R; r++) {
                int sum = 0;
                for (int c = 1; c <= C; c++) {
                    sum += arr[r][c];
                }
                minNum = Math.min(sum, minNum);
            }
        }
        
        // 순서 지정 순열
        for(int i=0;i<combi.length;i++){
            if(!visitedChoice[i]){
                visitedChoice[i] = true;
                choosed[nthChoice] = combi[i];
                solution(nthChoice+1, choosed, visitedChoice);
                visitedChoice[i] = false;
            }
        }
    }


    private static void rotateArr(int startR, int startC) {
        final int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int nr = startR;
        int nc = startC;
        for (int dir = 0; dir < deltas.length; ) {
            int before = arr[nr][nc];
            nr += deltas[dir][1];
            nc += deltas[dir][0];

            if (!scope(nr, nc) || visited[nr][nc]) {
                // 범위 벗어나거나 바깥배열에 닿으면 방향 전환
                nr -= deltas[dir][1];
                nc -= deltas[dir][0];
                dir++;
                continue;
            }
            tmpArr[nr][nc] = before;
            visited[nr][nc] = true;
        }
        tmpArr[startR][startC] = arr[startR + 1][startC];
    }

    private static boolean scope(int r, int c) {
        return r >= startR && r <= endR && c >= startC && c <= endC;
    }

    private static int[][] copyArr(int[][] tmp) {
        int[][] copied = new int[R + 1][C + 1];
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                copied[r][c] = tmp[r][c];
            }
        }
        return copied;
    }
}