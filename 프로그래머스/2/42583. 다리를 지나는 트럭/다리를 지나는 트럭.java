import java.util.*;
import java.io.*;

class Solution {
    static ArrayDeque<Integer> bridge;
    static ArrayDeque<Integer> waiting;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        bridge = new ArrayDeque<>();
        waiting = new ArrayDeque<>();
        
        int bridge_weight = 0;
        for(int i=0; i<truck_weights.length; i++) {
            waiting.offer(truck_weights[i]);
        }
        
        answer += bridge_length;
        for(int i=0; i<truck_weights.length; i++) {
            int cur = truck_weights[i];
            
            while(true) {
                if(bridge.isEmpty()) {
                    bridge_weight += cur;
                    bridge.offer(cur);
                    answer++;
                    break;
                } else if(bridge.size() == bridge_length) {
                    bridge_weight -= bridge.poll();
                } else {
                    if(bridge_weight + cur <= weight) {
                        bridge_weight += cur;
                        bridge.offer(cur);
                        answer++;
                        break;
                    } else {
                        bridge.offer(0);
                        answer++;
                    }
                }
            }
        }
        
        
        return answer;
    }
}