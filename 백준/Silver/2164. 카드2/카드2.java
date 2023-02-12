import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	static BufferedReader br;
	static int N;
	static Deque<Integer> dq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dq = new ArrayDeque<>();
		
		for(int i=1;i<=N;i++) {
			dq.addLast(i);
		}
		
		boolean discard = true;
		while(!dq.isEmpty()) {
			if(dq.size() == 1) {
				System.out.println(dq.removeFirst());
				return ;
			}
			if(discard) {
				dq.removeFirst();
				discard = false;
			} else {
				dq.addLast(dq.removeFirst());
				discard = true;
			}
		}

	}

}
