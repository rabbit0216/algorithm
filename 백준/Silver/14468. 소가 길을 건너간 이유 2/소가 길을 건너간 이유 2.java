import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] cows = br.readLine().toCharArray();
        int[] startPos = new int[27];
        int[] endPos = new int[27];

        for (int i = 0; i < cows.length; i++) {
            if (startPos[cows[i] - 'A'] != 0) {
                endPos[cows[i] - 'A'] = i + 1;
            } else {
                startPos[cows[i] - 'A'] = i + 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 26; i++) {
            int start = startPos[i];
            int end = endPos[i];

            for (int j = 0; j < 26; j++) {
                int nextCowStart = startPos[j];
                int nextCowEnd = endPos[j];
                if(nextCowStart > start && nextCowEnd > end && nextCowStart < end) {
                        result++;
                }
            }
        }

        bw.write(result+""); bw.flush(); bw.close();
    }
}