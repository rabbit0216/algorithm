import java.io.*;
import java.util.*;

/**
 * #위상정렬
 *
 * 키가 작은 사람이 키가 더 큰 사람 가리키게
 *
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb;
    static StringTokenizer st;
    static int N,M;
    static ArrayList<Integer>[] repres;
    static int[] indegree;
    static Queue<Integer> queue;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());

        repres = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            repres[i] = new ArrayList<>();
        }
        indegree = new int[N+1];
        queue = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
            repres[a].add(b);
            indegree[b]++;
        }


        sb = new StringBuilder();

        // 진입 차수 0인 애들 큐에 넣기
        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int num = queue.poll();
            sb.append(num + " ");

            for (int next: repres[num]) {
                if(--indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}