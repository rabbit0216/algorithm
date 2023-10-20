import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
        HashMap<String, Integer> playerScore = new HashMap<>();
        for(int i=0; i<players.length; i++) {
            playerScore.put(players[i], i);
        }
        
        for(int i=0; i<callings.length; i++) {
            int score = playerScore.get(callings[i]);
            String targetPlayer = players[score - 1];
            
            players[score] = targetPlayer;
            players[score - 1] = callings[i];
            
            playerScore.put(callings[i], score - 1);
            playerScore.put(targetPlayer, score);
        }
        
        answer = players;
        return answer;
    }
}