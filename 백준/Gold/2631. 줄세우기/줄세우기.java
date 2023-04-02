import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     * 1. 가장 큰 숫자 찾기
     * 2. 해당 숫자 기준 LIS 찾고(이분탐색), LIS 길이가 N이 되면 끝
     * 3. 바꿀때마다 cnt ++
     */
    static int N;
    static int[] children;
    static int maxNum = Integer.MIN_VALUE;
    static int maxIdx;
    static int[] C;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        children = new int[N];
        C = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
            if(maxNum < children[i]){
                maxNum = children[i];
                maxIdx = i;
            }
        }

        int size = 0;
        for (int i=0; i < N; i++) {

            int temp = Arrays.binarySearch(C, 0, size, children[i]); // 리턴값 : -insertPoint -1
            temp = Math.abs(temp)-1;//삽입위치
            C[temp] = children[i];// temp 자리에 값을 update 하면 그 의미는
            // 0인덱스 위치부터 temp위치까지의 원소 갯수가  temp위치에 저장된 그 값을 마지막으로 하는 LIS 길이가 됨
            // 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.

            if (size == temp) {// 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치라는 얘기임) 크기 1늘림.
                size++;
            }
        }
        System.out.println(N - size);



    }
}