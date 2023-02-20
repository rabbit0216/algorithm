import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, R, C;
	static int cnt = 0;
	static boolean flag = false;

	public static void search(int r, int c, int size) {
		if (flag)
			return;
		if (size == 1) {
			if (r == R && c == C ) {
				flag = true;
				return ;
			}
			cnt++;
			return;
		}
		// r,c가 쪼개져야 할까?
		if (r > R || R >= r + size || c > C || C >= c + size) {
			cnt += size * size;
			return ;
		}		
		int half = size / 2;
		search(r, c, half);
		search(r, c + half, half);
		search(r + half, c, half);
		search(r + half, c + half, half);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);
		search(0, 0, size);
		System.out.println(cnt);
	}
}