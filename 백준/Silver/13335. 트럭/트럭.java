import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int n;
    public static int bridgeLength;
    public static int maxWeight;

    public static class Truck{
        int weight;
        int idx;
        int remDist;

        public Truck(int weight, int idx, int remDist){
            this.weight = weight;
            this.idx = idx;
            this.remDist = remDist;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        bridgeLength = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        Queue<Truck> trucks = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < n; idx++){
            int weight = Integer.parseInt(st.nextToken());
            trucks.add(new Truck(weight,idx,bridgeLength));
        }

        int crossTime = 1;
        int curWeight = 0;
        Queue<Truck> bridge = new LinkedList<>();
        while(true){
            if(trucks.isEmpty() && bridge.isEmpty()){
                break;
            }
            if(!trucks.isEmpty()) {
                Truck cur = trucks.peek();
                if (curWeight + cur.weight <= maxWeight) {
                    trucks.poll();
                    bridge.add(cur);
                    curWeight += cur.weight;
                }
            }
            crossTime++;
            for(Truck truck : bridge){
                truck.remDist -= 1;
            }
            while(!bridge.isEmpty()){
                Truck firstTruck = bridge.peek();
                if(firstTruck.remDist == 0){
                    bridge.poll();
                    curWeight -= firstTruck.weight;
                }
                else{
                    break;
                }
            }


        }
        System.out.println(crossTime);
    }
}

