import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static String X;
	static char[] nums;
	static int minNum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		X = br.readLine();
		nums = new char[X.length()];
		
		for(int i=0;i<X.length();i++) {
			nums[i] = X.charAt(i);
		}
		
		solution(0, new char[nums.length], new boolean[nums.length]);
		if(minNum == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(minNum);
	}
	
	private static void solution(int nthChoice, char[] choosed, boolean[] visited) {
		if(nthChoice == choosed.length) {
			String number = "";
			for(int i=0;i<choosed.length;i++) {
				number += choosed[i] + "";
			}
			int tmpMinNum = Math.min(minNum, Integer.parseInt(number));
			if(tmpMinNum > Integer.parseInt(X)) {
				minNum = tmpMinNum;
			}
			return ;
		}
		
		for(int i=0;i<choosed.length;i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = nums[i];
				solution(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
}
