import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] nums;
	static StringBuilder output;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			nums = new int[k];
			for (int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			solution(0, new int[6], 0);
			output.append('\n');
		}
		System.out.println(output);
	}

	private static void solution(int nthChoice, int[] choosed, int startIdx) {
		if (nthChoice == choosed.length) {
			for (int i = 0; i < choosed.length; i++) {
				output.append(choosed[i]).append(" ");
			}
			output.append('\n');
			return;
		}

		for (int i = startIdx; i < nums.length; i++) {
			choosed[nthChoice] = nums[i];
			solution(nthChoice + 1, choosed, i + 1);
		}
	}
}
