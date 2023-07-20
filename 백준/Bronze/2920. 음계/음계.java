import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int UP = 1, DOWN = 2, MIXED = 3;
        int flag = UP;
        int[] sound = new int[8];
        for (int i = 0; i < 8; i++) {
            int n = Integer.parseInt(st.nextToken());
            sound[i] = n;
        }

        for (int i = 1; i < 8; i++) {
            if(sound[i-1] - sound[i] == 1){
                flag = DOWN;
            } else if(sound[i-1] - sound[i] != -1){
                flag = MIXED;
                break;
            } else if(flag == DOWN){
                flag = MIXED;
            }
        }

        if(flag == UP){
            System.out.println("ascending");
        } else if(flag == DOWN){
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }

    }
}