import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Grade implements Comparable<Grade> {
        int firstScore;
        int secondScore;

        public Grade(int firstScore, int secondScore) {
            this.firstScore = firstScore;
            this.secondScore = secondScore;
        }

        @Override
        public int compareTo(Grade g) {
            if (this.firstScore == g.firstScore) {
                return Integer.compare(this.secondScore, g.secondScore);
            } else {
                return Integer.compare(this.firstScore, g.firstScore);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int ans = N;
            Grade[] grades = new Grade[N];

            for (int std = 0; std < N; std++) {
                StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
                int firstScore = Integer.parseInt(stringTokenizer.nextToken());
                int secondScore = Integer.parseInt(stringTokenizer.nextToken());
                grades[std] = new Grade(firstScore, secondScore);
            }

            Arrays.sort(grades);

            int maxSecondScore = grades[0].secondScore;
            for (int std = 1; std < N; std++) {
                if (grades[std].secondScore >= maxSecondScore) {
                    ans--;
                } else {
                    maxSecondScore = grades[std].secondScore;
                }
            }


            bw.write(ans + "\n");
        }

        bw.flush();
        bw.close();
    }
}