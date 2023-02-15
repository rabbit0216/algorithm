import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int R, C, opeCnt;
	static int[][] arr;
	static int[][] tmpArr;
	static int opeSeq;
	static int N;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Math.max(R, C);
		opeCnt = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		tmpArr = new int[N][N];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < opeCnt; idx++) {
			opeSeq = Integer.parseInt(st.nextToken());
			switch (opeSeq) {
			case 1:
				rotate1();
				break;
			case 2:
				rotate2();
				break;
			case 3:
				rotate3();
				break;
			case 4:
				rotate4();
				break;
			case 5:
				rotate5();
				break;
			case 6:
				rotate6();
				break;
			}
		}
		
		int cntNums = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.printf(arr[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void rotate1() {
		tmpArr = copyArr(arr);
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tmpArr[r][c] = arr[R - r - 1][c];
				tmpArr[R - r - 1][c] = arr[r][c];
			}
		}
		arr = copyArr(tmpArr);
	}

	private static void rotate2() {
		tmpArr = copyArr(arr);
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tmpArr[r][c] = arr[r][C - 1 - c];
				tmpArr[r][C - 1 - c] = arr[r][c];
			}
		}
		arr = copyArr(tmpArr);
	}

	private static void rotate3() {
		int lineIdx = 0;
		for (int c = R - 1; c >= 0; c--) {
			int charIdx = 0;
			for (int r = 0; r < C; r++) {
				tmpArr[r][c] = arr[lineIdx][charIdx];
				charIdx++;
			}
			lineIdx++;
		}
		arr = copyArr(tmpArr);
		int tmpR = R;
		int tmpC = C;
		R = tmpC;
		C = tmpR;
	}

	private static void rotate4() {
		int lineIdx = C-1;
		for (int r = 0; r < C; r++) {
			int charIdx = 0;
			for (int c = 0; c < R; c++) {
				tmpArr[r][c] = arr[charIdx][lineIdx];
				charIdx++;
			}
			lineIdx--;
		}
		arr = copyArr(tmpArr);
		int tmpR = R;
		int tmpC = C;
		R = tmpC;
		C = tmpR;
	}

	private static void rotate5() {
		int midR = R / 2;
		int midC = C / 2;

		// 4->1
		for (int r = 0; r < midR; r++) {
			for (int c = 0; c < midC; c++) {
				tmpArr[r][c] = arr[midR + r][c];
			}
		}
		//1->2
		for (int r = 0; r < midR; r++) {
			for (int c = midC; c < C; c++) {
				tmpArr[r][c] = arr[r][c - midC];
			}
		}
		//2->3
		for (int r = midR; r < R; r++) {
			for (int c = midC; c < C; c++) {
				tmpArr[r][c] = arr[r - midR][c];
			}
		}
		//3->4
		for (int r = midR; r < R; r++) {
			for (int c = 0; c < midC; c++) {
				tmpArr[r][c] = arr[r][midC + c];
			}
		}
		arr = copyArr(tmpArr);
	}

	private static void rotate6() {
		int midR = R / 2;
		int midC = C / 2;

		//1->4
		for (int r = midR; r < R; r++) {
			for (int c = 0; c < midC; c++) {
				tmpArr[r][c] = arr[r - midR][c];
			}
		}
		//4->3
		for (int r = midR; r < R; r++) {
			for (int c = midC; c < C; c++) {
				tmpArr[r][c] = arr[r][c - midC];
			}
		}
		//3->2
		for (int r = 0; r < midR; r++) {
			for (int c = midC; c < C; c++) {
				tmpArr[r][c] = arr[r + midR][c];
			}
		}
		//2->1
		for (int r = 0; r < midR; r++) {
			for (int c = 0; c < midC; c++) {
				tmpArr[r][c] = arr[r][c + midC];
			}
		}
		arr = copyArr(tmpArr);
	}

	private static int[][] copyArr(int[][] tmp) {
		int[][] copied = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copied[r][c] = tmp[r][c];
			}
		}
		return copied;
	}

}