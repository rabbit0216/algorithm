import java.io.*;
import java.util.*;

class Solution {
    static Set<Integer> res;
    static int numsIdx;
    
    public int[] solution(int[] numbers) {
        int[] answer;
        res = new HashSet<>();
        
        makeCombi(0, new int [2], 0, numbers);
        Iterator iter = res.iterator();
        ArrayList<Integer> toSort = new ArrayList<>();
        while(iter.hasNext()){
            toSort.add((Integer)iter.next());
        }
        Collections.sort(toSort);
        answer = new int[toSort.size()];
        for(int i=0; i<toSort.size(); i++){
            answer[i] = toSort.get(i);
        }
        return answer;
    }
    
    
    
    static void makeCombi(int nthChoice, int[] choosed, int startIdx, int[] numbers){
        if(nthChoice == choosed.length){
            int sum = choosed[0] + choosed[1];
            res.add(sum);
            return ;
        }
        
        for(int i=startIdx; i<numbers.length; i++){
            choosed[nthChoice] = numbers[i];
            makeCombi(nthChoice + 1, choosed, i + 1, numbers);
        }
    }
}