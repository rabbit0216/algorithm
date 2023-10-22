import java.util.*;
import java.io.*;

class Solution {
    static int max;
    public String solution(int[] numbers) {
        String answer = "";
        String[] arr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(arr, (o1, o2) -> (o1 + o2).compareTo(o2 + o1) * -1); // 내림차순
        if(arr[0].equals("0")) {
            return arr[0];
        }
        
        StringBuilder sb = new StringBuilder();
        for(String num : arr) {
            sb.append(num);
        } 
        answer = sb.toString();
        return answer;
    }
}