import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * #세그먼트트리
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K, treeSize;
    static long[] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int h = (int) (Math.ceil((Math.log(N) / Math.log(2))));
        treeSize = (int) Math.pow(2, h);
        tree = new long[treeSize * 2];

        for (int i = treeSize; i < treeSize + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        fillTree();

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){ // b번째 수 -> c 로 변경
                changeNum(b,c);
            } else { // b ~ c 구간합
                long ans = calcSum(b, (int) c);
                sb.append(ans).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static void fillTree() {
        for (int i = tree.length - 1; i > 0; i--) {
            tree[i / 2] += tree[i];
        }
    }

    private static long calcSum(int from, int to) {
        from = treeSize + from - 1;
        to = treeSize + to - 1;
        long  sum = 0;
        while(from <= to){
            // start
            if(from % 2 == 0){ // 왼쪽 자식일 경우, 부모 값에 자신 포함되어 있음
                from /= 2;
            } else { // 오른쪽 자식일 경우, 부모 값에 자신 이외의 값 포함
                sum += tree[from];
                from = (from + 1) / 2;
            }

            // end
            if(to % 2 == 0) { // 왼쪽 자식일 경우, 부모 값에 자신 이외의 값 포함
                sum += tree[to];
                to = (to - 1) / 2;
            } else { // 오른쪽 자식일 경우, 부모 값에 자신 포함되어 있음
                to /= 2;
            }
        }

        return sum;
    }

    private static void changeNum(int where, long num) {
        where = treeSize + where - 1;
        long diff = num - tree[where];
        for (int i = where; i > 0 ; i /= 2) {
            tree[i] += diff;
        }
    }
}