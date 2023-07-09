import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    /**
     * #비트마스킹
     * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
     * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
     * check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
     * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
     * all: S를 {1, 2, ..., 20} 으로 바꾼다.
     * empty: S를 공집합으로 바꾼다.
     */

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int M;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        int nums = 1 << 21;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String menu = st.nextToken();
            int x;
            switch (menu){
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    nums |= 1 << x;
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    nums &= ~(1 << x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    if((nums & (1 << x)) > 1) {
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                    sb.append("\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    nums ^= 1 << x;
                    break;
                case "all":
                    nums = -1;
                    break;
                case "empty":
                    nums = 0;
                    break;
            }
        }

        bw.write(sb.toString()); bw.flush(); bw.close();

    }
}