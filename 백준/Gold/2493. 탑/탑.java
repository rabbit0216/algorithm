import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[] ans;
    static ArrayDeque<Node> stack;
    static StringBuilder output;

    static class Node {
        int height;
        int idx;

        Node(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        output = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        ans = new int[N + 1];
        stack = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());

        stack.addLast(new Node(Integer.parseInt(st.nextToken()), 1));
        for (int i = 2; i <= N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if(!stack.isEmpty()) {
                while(!stack.isEmpty()) {
                    if (cur > stack.peekLast().height) {
                        ans[i] = 0;
                        stack.pollLast();
                    } else {
                        Node top = stack.peekLast();
                        ans[i] = top.idx;
                        break;
                    }
                }
                stack.add(new Node(cur, i));
            }
        }

        for (int i = 1; i <= N; i++) {
            output.append(ans[i]).append(" ");
        }
        System.out.println(output);
    }

}