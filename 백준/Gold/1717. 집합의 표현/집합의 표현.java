import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /**
     * #union-find
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n,m;
    static int[] repres;
    final static int UNION = 0, CONTAIN = 1;
    public static void main(String[] args) throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        repres = new int[n+1];

        for (int i = 0; i <= n; i++) {
            repres[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(command == UNION){
                union(a,b);
            } else if(command == CONTAIN){
                if(findSet(a) == findSet(b)) {
                    sb.append("YES");
                } else {
                    sb.append("NO");
                }
                sb.append('\n');
            }
        }

        bw.write(sb.toString()); bw.flush(); bw.close();
    }

    private static int findSet(int a) {
        if(repres[a] == a) return a;
        return repres[a] = findSet(repres[a]);
    }

    private static void union(int a,int b){
        int rootA = findSet(a);
        int rootB = findSet(b);

        if(rootA != rootB)  {
            repres[rootA] = rootB;
        }
    }
}