import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static String[] words;
    static String ans;
    static StringBuilder ansSb = new StringBuilder();
    static boolean find;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int k = Integer.parseInt(br.readLine());
            words = new String[k];
            for (int wordSize = 0; wordSize < k; wordSize++) {
                String word = br.readLine();
                words[wordSize] = word;
            }
            find = false;
            makeCombi(0, new String[2], 0);

            if(!find) {
                ansSb.append(0).append('\n');
            }
        }
        bw.write(ansSb.toString()); bw.flush(); bw.close();
    }

    private static void makeCombi(int nthChoice, String[] choosed, int startIdx) {
        if(find) return;
        if(nthChoice == choosed.length) {
            if(checkSentence(choosed[0], choosed[1]) || checkSentence(choosed[1], choosed[0])) {
                ansSb.append(ans).append('\n');
                ans = "";
                find = true;
            }
            return;
        }

        for (int i = startIdx; i < words.length; i++) {
            choosed[nthChoice] = words[i];
            makeCombi(nthChoice + 1, choosed, i+1);
        }
    }

    private static boolean checkSentence(String s1, String s2) {
        String sentence = s1 + s2;
        int startPoint = 0;
        int endPoint = sentence.length() - 1;

        while(startPoint < endPoint) {
            char start = sentence.charAt(startPoint++);
            char end = sentence.charAt(endPoint--);

            if(start != end) return false;
        }

        ans = sentence;
        return true;
    }
}