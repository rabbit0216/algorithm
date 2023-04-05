import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * 1. 가로, 세로 나눠서 구현
     * 2. 경사로 앞, 뒤 구분해서 생성
     *
     * 3. 이미 생성된 경사로가 있으면 생성 불가
     * 4. 앞, 뒤 높이가 차가 1보다 크면 생성 불가
     * 5. cur ~ X 사이 높이가 다른게 존재하면 생성 불가
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,X;
    static int[][] graph;
    static boolean[] visited;


    public static void main(String[] args) throws Exception {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); X = Integer.parseInt(st.nextToken());
            graph = new int[N][N];
            visited = new boolean[N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 가로
            boolean canBuild;
            int ans = 0;
            for (int r = 0; r < N; r++) {
                canBuild = true;
                visited = new boolean[N];
                for (int c = 1; c < N; c++) {
                    if(graph[r][c] != graph[r][c-1]){ //다른 높이 만남
                        if(graph[r][c-1] - graph[r][c] == 1) { // 뒤에 경사로 만들 수 있는지 체크
                            for (int i = 0; i < X; i++) {
                                if(c+i >= N) { // 범위 벗어나면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(visited[c+i]) { // 이미 경사로가 지어져 있으면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(graph[r][c+i] != graph[r][c]) { // 다른 높이면 만들 수 없음
                                    canBuild = false;
                                    break;
                                }
                            }
                            if(canBuild){ // 경사로 만들었는지 체크
                                for (int i = c; i < c + X; i++) {
                                    visited[i] = true;
                                }
                            }
                        } else if(graph[r][c-1] - graph[r][c] == -1){ // 앞에 경사로 만들 수 있는지 체크
                            for (int i = 0; i < X; i++) {
                                if(c-1-i < 0) { // 범위 벗어나면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(visited[c-1-i]) { // 이미 경사로가 지어져 있으면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(graph[r][c-1-i] != graph[r][c-1]) { // 다른 높이면 만들 수 없음
                                    canBuild = false;
                                    break;
                                }
                            }
                            if(canBuild){ // 경사로 만들었는지 체크
                                for (int i = c-1; i > c - 1 - X; i--) {
                                    if(i < 0) {
                                        canBuild = false;
                                        break;
                                    }
                                    visited[i] = true;
                                }
                            }
                        }  else {
                            canBuild = false;
                        }
                        if(!canBuild) break; // 건설 불가능하면 더이상 볼 필요 없음
                    }
                }
                if(canBuild) {
//                    System.out.println(r + "번째 가로");
                    ans++;
                }
            }
            
            // 세로
            for (int c = 0; c < N; c++) {
                canBuild = true;
                visited = new boolean[N];
                for (int r = 1; r < N; r++) {
                    if(graph[r][c] != graph[r-1][c]){ //다른 높이 만남
                        if(graph[r-1][c] - graph[r][c] == 1) { // 뒤에 경사로 만들 수 있는지 체크
                            for (int i = 0; i < X; i++) {
                                if(r+i >= N) { // 범위 벗어나면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(visited[r+i]) { // 이미 경사로가 지어져 있으면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(graph[r+i][c] != graph[r][c]) { // 다른 높이면 만들 수 없음
                                    canBuild = false;
                                    break;
                                }
                            }
                            if(canBuild){ // 경사로 만들었는지 체크
                                for (int i = r; i < r + X; i++) {
                                    visited[i] = true;
                                }
                            }
                        } else if(graph[r-1][c] - graph[r][c] == -1){ // 앞에 경사로 만들 수 있는지 체크
                            for (int i = 0; i < X; i++) {
                                if(r-1-i < 0) { // 범위 벗어나면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(visited[r-1-i]) { // 이미 경사로가 지어져 있으면 안됨
                                    canBuild = false;
                                    break;
                                }
                                if(graph[r-1-i][c] != graph[r-1][c]) { // 다른 높이면 만들 수 없음
                                    canBuild = false;
                                    break;
                                }
                            }
                            if(canBuild){ // 경사로 만들었는지 체크
                                for (int i = r-1; i > r - 1 - X; i--) {
                                    if(i < 0) {
                                        canBuild = false;
                                        break;
                                    }
                                    visited[i] = true;
                                }
                            }
                        } else {
                            canBuild = false;
                        }
                        if(!canBuild) break; // 건설 불가능하면 더이상 볼 필요 없음
                    }
                }
                if(canBuild) {
//                    System.out.println(c + "번째 세로");
                    ans++;
                }
            }
        System.out.println(ans);
    }
}