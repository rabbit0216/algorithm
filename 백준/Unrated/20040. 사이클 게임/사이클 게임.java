import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * #union-find
     *
     * 1. 점과 점 연결 할 때마다 union
     * 2. false 면 사이클 형성 된 것, 답 저장하고 끝
     *
     * [입력]
     * 점의 개수 n :  3 ≤ n ≤ 500,000, 진행 된 차례 수 m : 3 ≤ m ≤ 1,000,000
     *
     * [출력]
     * m 번째 차례까지 게임을 진행한 상황에서, 이미 게임이 종료되었다면, 사이클이 처음으로 만들어진 차례의 번호
     * 종료되지 않았다면 '0'
     */

    static BufferedReader br;
    static StringTokenizer st;
    static int[] repres; // 대표자 선언
    static int N,M;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());

        repres = new int[N];
        for (int i = 0; i < N; i++) {
            repres[i] = i; // 자기 자신으로 대표자 선언
        }

        int turn = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
            if(!union(a,b)) {
                System.out.println(turn + 1);
                return ;
            } else {
                turn++;
            }
        }
        System.out.println(0);
    }

    private static int findSet(int v){
        if(repres[v] == v) return v;
        return repres[v] = findSet(repres[v]);
    }

    private static boolean union(int a,int b){
        int rootA = findSet(a);
        int rootB = findSet(b);

        if(rootA == rootB) return false;
        else {
            repres[rootA] = rootB;
            return true;
        }
    }
}