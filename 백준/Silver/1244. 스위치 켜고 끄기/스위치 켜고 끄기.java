import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static final int BOY = 1;
    public static final int GIRL = 2;

    public static class Node {
        int bg, num;

        Node(int bg, int num) {
            this.bg = bg;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] switches = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int stdN = Integer.parseInt(st.nextToken());
        ArrayList<Node> std = new ArrayList<>();

        for (int i = 0; i < stdN; i++) {
            st = new StringTokenizer(br.readLine());
            int bg = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            std.add(new Node(bg, num));
        }

        for (Node node : std) {
            if (node.bg == BOY) {
                int num = node.num;
                for (int idx = num; idx < switches.length; idx += num) {
                    switches[idx] = switches[idx] == 1 ? 0 : 1;
                }
            } else {
                int num = node.num;
                int idx = 1;
                switches[num] = switches[num] == 1 ? 0 : 1;
                while (true) {
                    if(idx + num > N || num - idx < 1) break;
                    if (switches[num - idx] == switches[num + idx]) {
                        switches[num - idx] = switches[num - idx] == 1 ? 0 : 1;
                        switches[num + idx] = switches[num + idx] == 1 ? 0 : 1;
                        idx++;
                    } else {
                        break;
                    }
                }
            }
        }

        for (int idx = 1; idx <= N; idx++) {
            System.out.print(switches[idx] + " ");
            if(idx % 20 == 0) System.out.println();
        }
    }
}