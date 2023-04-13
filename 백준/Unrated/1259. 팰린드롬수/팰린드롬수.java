import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        while(true){
            String s = br.readLine();
            if(s.equals("0")) break;

            int start = 0;
            int end = s.length() - 1;
            boolean flag = false;
            while(start < end){
                if(s.charAt(start) == s.charAt(end)){
                    start++; end--;
                } else {
                    sb.append("no").append('\n');
                    flag = true;
                    break;
                }
            }

            if(!flag){
                sb.append("yes").append('\n');
            }
        }
        System.out.println(sb);
    }
}
