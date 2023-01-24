import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Jewel {
        long kg;
        long price;

        Jewel(long kg, long price){
            this.kg = kg;
            this.price = price;
        }

        @Override
        public String toString() {
            return "[ " + this.kg + ": " + this.price + " ]";
        }


    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //보석: 가격 높은 순 정렬; 가방: 무게 가벼운 순 정렬
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Jewel[] jewel = new Jewel[n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            long kg = Long.parseLong(st.nextToken());
            long price = Long.parseLong(st.nextToken());
            jewel[i] = new Jewel(kg,price);
        }

        PriorityQueue<Jewel> pq = new PriorityQueue<>(new Comparator<Jewel>() {
            @Override
            public int compare(Jewel j1, Jewel j2) {
                if(j1.price < j2.price) {
                    return 1;
                }
                else if(j1.price > j2.price){
                    return -1;
                }
                else return 0;
            }
        });
        long priceSum = 0;

        long[] bag = new long[k];
        for(int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            long kg = Integer.parseInt(st.nextToken());
            bag[i] = kg;
        }
        Arrays.sort(bag);

        //가져갈 수 있는 보석 모두 pq에 넣기 (무게 작거나 같은것)
        Arrays.sort(jewel,new Comparator<Jewel>() {
            @Override
            public int compare(Jewel j1, Jewel j2) {
                if(j1.kg > j2.kg) {
                    return 1;
                }
                else if(j1.kg < j2.kg) {
                    return -1;
                }
                else{
                    return 0;
                }
            }
        });

        //중복된 값 넣지 않게 idx 변수를 통해 보석 탐색, 다음 가방 무게 비교할 떄 이미 큐에 들어가 있는 보석은 idx 증가되어서 그 다음 idx부터 탐색하게 해줌
        int idx = 0;
        for(int bagIdx = 0; bagIdx < k; bagIdx++) {
                for(int jewelIdx = idx; jewelIdx < n; jewelIdx++) {
                    if(bag[bagIdx] >= jewel[jewelIdx].kg){
                        pq.add(jewel[jewelIdx]);
                        idx++;
                    }
                    else{
                        break;
                    }
                }
                if(!pq.isEmpty()){
                    priceSum += pq.poll().price;
                }
        }

        System.out.println(priceSum);

    }

}
