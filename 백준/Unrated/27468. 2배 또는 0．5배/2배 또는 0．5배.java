import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[N+1];
        boolean isOne = true;
        int num = 1;
        visited[num] = true;
        sb.append(num).append(' ');
        for (int fix = 0; fix < N-1; fix++) {
            if(isOne){
                num += 1;
                if(visited[num]){
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
                num -= 2;
                isOne = true;
            }
//            System.out.println(sb);
        }
        String ans = "";
//        System.out.println(Arrays.toString(visited));
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                ans = "NO";
                System.out.println(ans);
                return ;
            }
        }
        System.out.println("YES");
        System.out.println(sb);
    }
}
