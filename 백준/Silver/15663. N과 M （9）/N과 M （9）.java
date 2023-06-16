import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static Set<Integer> set;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        makePerm(0, new int[M], new boolean[N]);
        bw.flush(); bw.close();

    }

    private static void makePerm(int nthChoice, int[] choosed, boolean[] visited) throws IOException {
        if(nthChoice == choosed.length){
            for (int i = 0; i < choosed.length; i++) {
                bw.write(choosed[i] + " ");
            }
            bw.write('\n');
            return ;
        }

        int before = 0;
        for (int i = 0; i < list.size(); i++) {
            if(!visited[i]) {
                if(list.get(i) == before) continue;
                visited[i] = true;
                choosed[nthChoice] = list.get(i);
                before = list.get(i);
                makePerm(nthChoice + 1, choosed, visited);
                visited[i] = false;
            }
        }
    }
}