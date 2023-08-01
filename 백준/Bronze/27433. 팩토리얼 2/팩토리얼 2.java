import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Long ans = fact(N);
        System.out.println(ans);
    }

    private static long fact(int n) {
        if(n <= 1) return 1;
        return n * fact(n-1);
    }
}