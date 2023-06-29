import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(); int b = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        sb.append(a+b).append('\n').append(a-b).append('\n').append(a*b).append('\n').append(a/b).append('\n').append(a%b);
        System.out.println(sb.toString());
    }
}