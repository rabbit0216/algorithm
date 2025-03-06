import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());
        int ans = -1;
        while (++X <= 9999) {
            int front = X / 100;
            int back = X % 100;

            if(Math.pow(front + back, 2) == X) {
                ans = X;
                break;
            }
        }

        bw.write(ans+""); bw.flush(); bw.close();
    }
}
