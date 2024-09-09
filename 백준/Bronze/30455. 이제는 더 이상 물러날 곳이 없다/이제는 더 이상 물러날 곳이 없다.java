import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        final String gunduck = "Duck";
        final String gungoose = "Goose";

        if(N%2==0) {
            bw.write(gunduck);
        } else {
            bw.write(gungoose);
        }

        bw.flush(); bw.close();
    }
}