import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hour, minute;
        hour = Integer.parseInt(st.nextToken()); minute = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(br.readLine());

        minute = minute + time;
        if(minute >= 60) {
            hour += minute / 60;
            minute = minute % 60;
        }

        if(hour >= 24) {
            hour %= 24;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(hour).append(' ').append(minute);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString()); bw.flush(); bw.close();
    }

}