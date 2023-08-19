import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static ArrayDeque<Integer> deque;
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String command = br.readLine();
            deque = new ArrayDeque<>();

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
            while(st.hasMoreTokens()) {
                deque.offerLast(Integer.parseInt(st.nextToken()));
            }

            boolean isFront = true;
            boolean isError = false;
            for (int j = 0; j < command.length(); j++) {
                char cmd = command.charAt(j);
                if(cmd == 'R') {
                    if(isFront) {
                        isFront = false;
                    } else {
                        isFront = true;
                    }
                } else {
                    if(deque.isEmpty()) {
                        sb.append("error").append('\n');
                        isError = true;
                        break;
                    }
                    if(isFront) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }

            if(isError) continue;
            sb.append("[");
            while(!deque.isEmpty()) {
                if(isFront) {
                    sb.append(deque.pollFirst());
                } else {
                    sb.append(deque.pollLast());
                }
                if(!deque.isEmpty()) {
                    sb.append(",");
                }
            }
            sb.append("]").append('\n');
        }
        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}