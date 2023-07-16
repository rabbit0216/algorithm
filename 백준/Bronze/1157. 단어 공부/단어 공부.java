import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();

        word = word.trim();
        word = word.toUpperCase();

        long[] count = new long[26];

        char ans = 0;
        long maxCnt = 0L;
        for (int i = 0; i < word.length(); i++) {
            char alphabet = word.charAt(i);
            int idx = alphabet - 'A';
            if (++count[idx] > maxCnt) {
                maxCnt = count[idx];
                ans = alphabet;
            } else if (count[idx] == maxCnt) {
                ans = '?';
            }
        }

//        System.out.println(Arrays.toString(count));

        System.out.println(ans);
    }
}