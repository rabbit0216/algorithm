import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /**
     * #위상정렬?
     *
     * @param args
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N,M;
    static ArrayList<Integer>[] repres;
    static int[] indegree;
    static Queue<Integer> queue;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        repres = new ArrayList[N+1];
        indegree = new int[N+1];
        for (int i = 0; i <= N; i++) {
            repres[i] = new ArrayList<>();
        }
        queue = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int singer = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int next = Integer.parseInt(st.nextToken());
                repres[before].add(next);
                indegree[next]++;
                before = next;
            }
        }

        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            cnt++;
            sb.append(cur).append("\n");
            for (int next: repres[cur]) {
                if(--indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        if(cnt != N) bw.write(0+"");
        else bw.write(sb.toString());
        bw.flush(); bw.close();
    }
}