import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sampleN = Integer.parseInt(st.nextToken()); int systemN = Integer.parseInt(st.nextToken());

        for (int i = 0; i < sampleN; i++) {
            st = new StringTokenizer(br.readLine());
            String answer = st.nextToken();
            String manyoung = st.nextToken();

            if(!answer.equals(manyoung)) {
                bw.write("Wrong Answer"); bw.flush(); bw.close();
                return;
            }
        }

        for (int i = 0; i < systemN; i++) {
            st = new StringTokenizer(br.readLine());
            String answer = st.nextToken();
            String manyoung = st.nextToken();

            if(!answer.equals(manyoung)) {
                bw.write("Why Wrong!!!"); bw.flush(); bw.close();
                return;
            }
        }

        bw.write("Accepted"); bw.flush(); bw.close();
    }
}
