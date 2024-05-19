import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String sent = br.readLine();
            ArrayDeque<Character> deque = new ArrayDeque<>();
            for (int j = 0; j < sent.length(); j++) {
                char cur = sent.charAt(j);
                if (deque.isEmpty()) {
                    deque.offerLast(cur);
                } else {
                    char before = deque.peekLast();
                    if ((before == '(' && cur == ')')) {
                        deque.pollLast();
                    } else {
                        deque.offerLast(cur);
                    }
                }
            }
            if (deque.isEmpty()) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
    }
}