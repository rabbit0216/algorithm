import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] scores = new int[50];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static HashMap<Integer, Integer> map;
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        map = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
            map.put(scores[i], i+1);
        }

        int hongik = Integer.parseInt(br.readLine());
        int num = map.get(hongik);

        StringBuilder sb = new StringBuilder();
        if(num >=1 && num <= 5){
            bw.write("A+");
        } else if(num >=6 && num <= 15){
            bw.write("A0");
        } else if(num >=16 && num <= 30){
            bw.write("B+");
        } else if(num >= 31 && num <= 35){
            bw.write("B0");
        } else if(num >= 36 && num <= 45){
            bw.write("C+");
        } else if(num >= 46 && num <= 48){
            bw.write("C0");
        } else {
            bw.write("F");
        }
        bw.flush();
        bw.close();
    }
}