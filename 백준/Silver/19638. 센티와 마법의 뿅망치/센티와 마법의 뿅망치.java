import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); int centiHeight = Integer.parseInt(st.nextToken()); int T = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a,b) * -1;
            }
        });

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }


        int cnt = 0;
        while(!pq.isEmpty()) {
            int cur = pq.poll();

            if(cur < centiHeight) {
                pq.offer(cur);
                break;
            }

            if(cur == 1) {
                pq.offer(cur);
                break;
            }

            if(cnt < T) {
                pq.offer(cur / 2);
                cnt++;
            } else {
                pq.offer(cur);
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        int highest = pq.poll();
        if(highest >= centiHeight) {
            sb.append("NO").append('\n').append(highest);
            ;
        } else {
            sb.append("YES").append('\n').append(cnt);
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}
