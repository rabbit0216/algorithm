import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("??!");
        System.out.println(sb.toString());
    }
}