import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String listen = br.readLine();
            map.put(listen, 1);
        }

        int ans = 0;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String look = br.readLine();
            if (map.containsKey(look)) {
                ans++;
                names.add(look);
            }
        }
        Collections.sort(names);

        for (String name : names) {
            sb.append('\n').append(name);
        }

        bw.write(ans + "");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}