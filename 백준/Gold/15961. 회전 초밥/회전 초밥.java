import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int plateNum, chobabD, continualPlateK, couponNum;
    static int[] plates;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        plateNum = Integer.parseInt(st.nextToken());
        chobabD = Integer.parseInt(st.nextToken());
        continualPlateK = Integer.parseInt(st.nextToken());
        couponNum = Integer.parseInt(st.nextToken());
        plates = new int[plateNum];

        for (int i = 0; i < plateNum; i++) {
            st = new StringTokenizer(br.readLine());
            plates[i] = Integer.parseInt(st.nextToken());
        }

        int maxAns = 0;
        int start = 0;
        // key : 초밥 번호, value : 초밥 번호 등장 횟수
        HashMap<Integer, Integer> chobab = new HashMap<>();
        // 처음 window 세트 저장
        for (int i = 0; i < continualPlateK; i++) {
            if (chobab.containsKey(plates[i])) {
                chobab.put(plates[i], chobab.get(plates[i]) + 1);
            } else {
                chobab.put(plates[i], 1);
            }
        }
        int end = start + continualPlateK;
        while (start < plateNum) {
            int ans = chobab.size();
            // 연속된 초밥 접시 중 쿠폰 번호와 동일한 접시 있으면 -1
            if (chobab.containsKey(couponNum)) ans -= 1;
            maxAns = Math.max(ans, maxAns);
            // 동일한 번호 존재 시 등장 횟수 차감, 1이면 window에서 삭제
            if (chobab.get(plates[start]) > 1) {
                chobab.put(plates[start], chobab.get(plates[start]) - 1);
            } else {
                chobab.remove(plates[start]);
            }
            // 이미 window에 동일한 번호 존재 시 등장 횟수 증가
            if (chobab.containsKey(plates[end % plateNum])) {
                chobab.put(plates[end % plateNum], chobab.get(plates[end % plateNum]) + 1);
            } else {
                chobab.put(plates[end % plateNum], 1);
            }
            ++start;
            ++end;
        }

        System.out.println(maxAns + 1);
    }
}