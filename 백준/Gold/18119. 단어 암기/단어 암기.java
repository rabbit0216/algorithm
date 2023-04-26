import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] words;
    static int N, M;


    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        words = new int[N];
        String vowel = "aeiou";
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                char alp = word.charAt(j);
                words[i] |= 1 << alp - 'a';
            }
        }

        int remember = (1 << 27) - 1;
        for (int i = 0; i < M; i++) {
            int ans = N;
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            char alp = st.nextToken().charAt(0);

            if (vowel.contains(alp + "")) continue;

            if (command == 1) {
                remember &= ~(1 << alp - 'a');
            } else {
                remember |= 1 << alp -'a';
            }

            for (int j = 0; j < words.length; j++) {
                if((words[j] & remember) < words[j]) ans--;
            }

            sb.append(ans).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}