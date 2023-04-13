import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] repres;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        repres = new int[N+1];

        for (int i = 1; i <= N; i++) {
            repres[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); int to = Integer.parseInt(st.nextToken());
            union(from, to);
        }

        int ans = 0;

        for (int i = 1; i <= N ; i++) {
            if(repres[i] == i){
                ans++;
            }
        }

        System.out.println(ans);

    }

    static int findSet(int v){
        if(repres[v] == v) return v;
        return repres[v] = findSet(repres[v]);
    }

    static void union(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);

        if(rootA != rootB) {
            repres[rootA] = rootB;
        }
    }
}
