import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    /**
     * 1. 모음 갯수 카운팅 배열 -> 모음 나오면 카운팅 + 1 1-1. a, e, i, o, u 순 2. 특정 인덱스 +1, -1 이 같은 모음인지 자음인지 판별 3.
     * 특정 인덱스 +1, -1 같은 문자인지 판별, (e, o 인 경우 pass)
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb;
    static String vowels = "aeiou";

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        while (true) {
            String password = br.readLine();
            boolean containVowel = false;
            if (password.equals("end")) {
                break;
            }

            boolean acceptable = true;
            if (password.length() > 1) {
                if(vowels.contains(password.charAt(0)+"")) {
                    containVowel = true;
                }

                for (int i = 1; i <= password.length() - 1; i++) {
                    int prev = i - 1;
                    int next = i + 1;

                    char prevC = password.charAt(prev);
                    char nextC;
                    char curC = password.charAt(i);

                    if(vowels.contains(curC+"")) {
                        containVowel = true;
                    }

                    if ((curC != 'e' && curC != 'o') && (prevC == curC)) {
                        acceptable = false;
                        break;
                    }

                    if (i + 1 <= password.length() - 1) {
                        nextC = password.charAt(next);

                        // ee, oo 제외, 앞 뒤 같은지 확인
                        if ((curC != 'e' && curC != 'o') && (curC == nextC)) {
                            acceptable = false;
                            break;
                        }

                        if (vowels.contains(curC + "") && vowels.contains(prevC + "")
                            && vowels.contains(nextC + "")) {
                            acceptable = false;
                            break;
                        }

                        if (!vowels.contains(curC + "") && !vowels.contains(prevC + "")
                            && !vowels.contains(nextC + "")) {
                            acceptable = false;
                            break;
                        }
                    }
                }
            } else {
                if(vowels.contains(password)) {
                    containVowel = true;
                }
            }

            if(!containVowel) {
                acceptable = false;
            }

            sb.append("<").append(password).append(">").append(" is ");
            if (acceptable) {
                sb.append("acceptable.");
            } else {
                sb.append("not acceptable.");
            }
            sb.append('\n');

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}