import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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
        HashMap<Integer, Integer> chobab = new HashMap<>();
        boolean sameCouponNum = false;

        for (int i = 0; i < continualPlateK; i++) {
            if (plates[i] == couponNum) sameCouponNum = true;
            if (chobab.containsKey(plates[i])) {
                chobab.put(plates[i], chobab.get(plates[i]) + 1);
            } else {
                chobab.put(plates[i], 1);
            }
        }
        boolean ansFlag = false;
        int end = start + continualPlateK;
        while (start < plateNum) {
            int ans = chobab.size();
            if (chobab.containsKey(couponNum)) ans -= 1;
            maxAns = Math.max(ans, maxAns);
            if (chobab.get(plates[start]) > 1) {
                chobab.put(plates[start], chobab.get(plates[start]) - 1);
            } else {
                if (plates[end % plateNum] == couponNum) {
                    sameCouponNum = false;
                }
                chobab.remove(plates[start]);
            }

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