import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int stdNum = Integer.parseInt(br.readLine());
        boolean[][] sameClassFriends = new boolean[stdNum][stdNum]; // 같은 반이었던 친구들 저장
        ArrayList<Integer>[][] friendNums = new ArrayList[5][10]; // 학년 반
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                friendNums[i][j] = new ArrayList<>();
            }
        }


        int[][] classData = new int[stdNum][5]; // 각 학생들 반 정보 저장
        for (int std = 0; std < stdNum; std++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int grade = 0; grade < 5; grade++) {
                int classNum = Integer.parseInt(st.nextToken());
                friendNums[grade][classNum].add(std);
                classData[std][grade] = classNum;
            }
        }

        for (int std = 0; std < stdNum; std++) {
            for (int grade = 0; grade < 5; grade++) {
                int myClass = classData[std][grade];
                ArrayList<Integer> classFriends = friendNums[grade][myClass];
                for(Integer friend: classFriends) {
                    sameClassFriends[std][friend] = true;
                }
            }
        }

        int result = 0;
        int max = Integer.MIN_VALUE;
        for (int std = 0; std < stdNum; std++) {
            int cnt = 0;
            for (int friend = 0; friend < stdNum; friend++) {
                if(sameClassFriends[std][friend]) {
                    cnt++;
                }
            }
            if(max < cnt) {
                max = cnt;
                result = std + 1;
            }
        }

        bw.write(result+""); bw.flush(); bw.close();
    }
}