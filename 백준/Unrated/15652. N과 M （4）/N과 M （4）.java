import java.util.Scanner;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        makeCombi(0,new int[M], 1);
        System.out.println(sb);
    }
    private static void makeCombi(int nthChoice, int[] choice, int startIdx){
        if(nthChoice == choice.length){
            print(choice);
            return;
        }

        for (int i = startIdx; i <= N; i++) {
            choice[nthChoice] = i;
            makeCombi(nthChoice+1, choice, i);
        }
    }
    private static void print(int[] choice){
        for (int i = 0; i < choice.length; i++) {
            sb.append(choice[i]).append(' ');
        }
        sb.append('\n');
    }
}