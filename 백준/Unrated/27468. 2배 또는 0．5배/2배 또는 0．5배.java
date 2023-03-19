import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    static int N;
    static boolean[] visited;
    static boolean ansFlag;
    static StringBuilder sb;

    /**
     * 1. 1 또는 2의 차이로 조건을 만족하는 수열을 만들 수 있음
     * 2. 1, 2씩 번갈아가면서 증가시켜 1~N까지의 모든 수가 출력 됐는지 확인
     *  2-1.  1 - 2 순
     *  2-2. 2-1 완료 후, 전부 방문하지 않았다면 2 - 1 순으로 다시 체크
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        visited = new boolean[N+1];

        // 1부터
        findAns(true);
        checkVisited();
        if(!ansFlag) { // 못만들었다면 2 - 1 순으로
            findAns(false);
            checkVisited();
        }
        String ans = "";
        if(!ansFlag){
            ans = "NO";
        } else {
            ans = "YES";
        }
        System.out.println(ans);
        System.out.println(sb);
    }

    private static void findAns(boolean isOne) {
        sb = new StringBuilder();
        visited = new boolean[N+1];
        int num = 1;
        visited[num] = true;
        sb.append(num).append(' ');
        for (int fix = 0; fix < N-1; fix++) {
            if(isOne){
                num += 1;
                if(visited[num]){ // 이미 방문했던 숫자면 그 다음(2+1 뒤) 숫자 넣기
                    sb.append(num+=2);
                }
                else {
                    sb.append(num);
                }
                sb.append(' ');
                if(num > N) break;
                visited[num] = true;
                isOne = false;
            } else {
                num += 2;
                sb.append(num).append(' ');
                if(num > N) break;
                visited[num] = true;
                num -= 2; // 이전에 방문 안한 숫자 있을 수 있으므로 -2
                isOne = true;
            }
        }
    }
    private static void checkVisited() {
        ansFlag = true;
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                ansFlag = false;
                return ;
            }
        }
    }
}
