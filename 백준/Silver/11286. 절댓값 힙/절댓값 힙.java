import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder output;
	static int N;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		output = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int o1Abs = Math.abs(o1);
				int o2Abs = Math.abs(o2);
				if (o1Abs > o2Abs)
					return 1;
				else if (o1Abs == o2Abs) {
					if (o1 > o2) {
						return 1;
					} else if (o1 < o2) {
						return -1;
					} else {
						return 0;
					}
				} else {
					return -1;
				}
			}

		});

		int menu;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			menu = Integer.parseInt(st.nextToken());
			if (menu == 0) {
				// pop
				if (pq.isEmpty())
					output.append(0);
				else
					output.append(pq.poll());
				output.append('\n');
			} else {
				pq.add(menu);
			}
		}
		System.out.println(output);

	}

}