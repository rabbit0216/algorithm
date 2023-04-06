import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * #완탐, #조합 => 중복 조합으로 해야 할듯..? , 그래서 안함
     * 1. 별똥별 2개 조합, 두개 거리가 트램펄린 사이즈보다 크면 가지치기
     * 2. 트램펄린 내부에 다른 별똥별들이 포함되어이 있으면 카운팅
     * 3. 최대값 출력
     * 
     * 그냥 3중 for문으로 완료
     * 1. 2개 별을 각각 트램펄린 모서리로 설정
     * @param args
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R,C,L,N;
    static Node[] stars;
    static int maxStars;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());

        stars = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); int c = Integer.parseInt(st.nextToken());
            stars[i] = new Node(r,c);
        }
        maxStars = Integer.MIN_VALUE;

        for (int first = 0; first < N; first++) {
            Node firstStar = stars[first];
            for (int second = 0; second < N; second++) {
                Node secondStar = stars[second];

                int r = firstStar.r; int c = secondStar.c;
                int cnt = 0;
                for (int target = 0; target < N; target++) {
                    Node targetStar = stars[target];
                    int tr = targetStar.r; int tc = targetStar.c;
                    if(tr >= r && tr <= r+L && tc <= c && tc >= c-L) cnt++;
                }
                maxStars = Math.max(maxStars, cnt);
            }
        }
        System.out.println(N - maxStars);
    }


    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}