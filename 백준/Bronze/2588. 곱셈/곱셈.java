import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int[] ans = new int[4];
        ans[0] = a * (b % 10);
        ans[1] = a * ((b % 100 - b % 10) / 10);
        ans[2] = a * (b / 100);
        ans[3] = a * b;

        for (int i = 0; i < 4; i++) {
            System.out.println(ans[i]);
        }
    }
}