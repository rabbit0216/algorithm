import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] arr;
	static StringBuilder sb;
	static boolean isEnd;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		arr = new int[9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());			
		}
		makePerm(0, new int[7], new boolean[9]);
		System.out.println(sb);
	}
	private static void makePerm(int nthChoice, int[] choosed, boolean[] visited) {
		if(isEnd) return ;
		if(nthChoice == choosed.length) {
			int sum = 0;
			for(int i=0;i<choosed.length;i++) {
				sum += choosed[i];
			}
			if(sum == 100) {
				Arrays.sort(choosed);
				for(int i=0;i<choosed.length;i++) {
					sb.append(choosed[i]).append('\n');
				}
				isEnd = true;
			}
			return ;
		}
		for(int i=0;i<visited.length;i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = arr[i];
				makePerm(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
}