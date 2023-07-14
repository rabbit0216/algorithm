import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String grade = sc.next();
        double ans = 0L;
        if(("A+").equals(grade)){
            ans = 4.3;
        } else if("A0".equals(grade)){
            ans = 4.0;
        } else if("A-".equals(grade)){
            ans = 3.7;
        } else if("B+".equals(grade)){
            ans = 3.3;
        } else if("B0".equals(grade)){
            ans = 3.0;
        } else if("B-".equals(grade)){
            ans = 2.7;
        } else if("C+".equals(grade)){
            ans = 2.3;
        } else if("C0".equals(grade)){
            ans = 2.0;
        } else if("C-".equals(grade)){
            ans = 1.7;
        } else if("D+".equals(grade)){
            ans = 1.3;
        } else if("D0".equals(grade)){
            ans = 1.0;
        } else if("D-".equals(grade)){
            ans = 0.7;
        } else if("F".equals(grade)){
            ans = 0.0;
        }
        System.out.println(ans);
    }
}