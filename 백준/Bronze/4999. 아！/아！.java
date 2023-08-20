import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String jaehwan = scanner.nextLine();
        String docter = scanner.nextLine();

        if(jaehwan.length() < docter.length()) {
            System.out.println("no");
        } else {
            System.out.println("go");
        }

    }
}