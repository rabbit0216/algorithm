import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Deque<Integer> queue;
    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(br.readLine());
        queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int n;
            if(command.equals("push")){
                n = Integer.parseInt(st.nextToken());
                queue.offer(n);
            } else if(command.equals("pop")) {
                if(queue.isEmpty()){
                    sb.append("-1");
                } else {
                    sb.append(queue.pollFirst());
                }
                sb.append("\n");
            } else if(command.equals("size")){
                sb.append(queue.size()).append('\n');
            } else if(command.equals("empty")){
                if(queue.isEmpty()){
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                sb.append("\n");
            } else if(command.equals("front")){
                if(queue.isEmpty()) {
                    sb.append("-1");
                } else {
                    sb.append(queue.peekFirst());
                }
                sb.append("\n");
            } else if(command.equals("back")){
                if(queue.isEmpty()) {
                    sb.append("-1");
                } else {
                    sb.append(queue.peekLast());
                }
                sb.append("\n");
            }

        }
        bw.write(sb.toString()); bw.flush(); bw.close();
    }
}
