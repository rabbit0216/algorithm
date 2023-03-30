import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int org, target;
    static int[] splitOrg;
    static boolean[] prime;
    static boolean[] visited;
    static Queue<int[]> queue;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        prime = new boolean[10000];
        Arrays.fill(prime, true);
        makePrime();
//        System.out.println(Arrays.toString(prime));
        for (int i = 0; i < T; i++) {
            ans = -1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            visited = new boolean[10000];
            splitOrg = new int[4];
            String orgS = st.nextToken();
            for (int j = 0; j < 4; j++) {
                splitOrg[j] = orgS.charAt(j) - '0';
            }
            org = Integer.parseInt(orgS);
            target = Integer.parseInt(st.nextToken());
            queue = new ArrayDeque<>();
            queue.offer(new int[]{org, 0});
            visited[org] = true;
            makeTarget();
            if(ans == -1) sb.append("Impossible");
            else sb.append(ans);
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void makePrime() {
        for (int i = 2; i * i <= 9999; i++) {
            if (!prime[i]) continue;
            for (int j = i * i; j <= 9999; j += i) {
                if (prime[j]) prime[j] = false;
            }
        }
    }

    static void makeTarget() {
        int num = org;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == target) {
                ans = cur[1];
                return;
            }
            for (int place = 0; place < 4; place++) {
                for (int i = 0; i <= 9; i++) {
                    if (place == 0 && i == 0) continue;
                    int[] splited = split(cur[0]);
                    splited[place] = i;
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < 4; j++) {
                        sb.append(splited[j]);
                    }
                    num = Integer.parseInt(sb.toString());
                    if (prime[num] && !visited[num]) {
                        queue.offer(new int[]{num, cur[1] + 1});
                        visited[num] = true;
                    }
                }
            }
        }
    }

    private static int[] split(int num) {
        int[] splited = new int[4];
        splited[0] = num % 10000 / 1000;
        splited[1] = num % 1000 / 100;
        splited[2] = num % 100 / 10;
        splited[3] = num % 10;
        return splited;
    }
}