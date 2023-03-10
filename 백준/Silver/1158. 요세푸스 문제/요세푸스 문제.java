import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> tmp = new LinkedList<>();
        Queue<Integer> ans = new LinkedList<>();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int cnt = 0;
        while (true) {
            cnt++;
            if (q.isEmpty() && !tmp.isEmpty()) {
                // 데이터 옮기기
                for (Integer num : tmp) {
                    q.add(num);
                }
                tmp.clear();
            }

            // 원소 한개만 남아있으면 끝
            if (q.size() == 1 && tmp.isEmpty()) {
                ans.add(q.poll());
                break;
            }
            if (cnt == K) {
                cnt = 0;
                ans.add(q.poll());
                continue;
            }
            tmp.add(q.poll());
        }

        // 출력
        sb.append("<");
        while (!ans.isEmpty()) {
            sb.append(ans.poll());
            if (ans.size() >= 1) {
                sb.append(",").append(" ");
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}

