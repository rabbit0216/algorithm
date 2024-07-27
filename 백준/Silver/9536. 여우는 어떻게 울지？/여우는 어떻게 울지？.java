import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static String END = "what does the fox say?";
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        HashMap<String, Integer> soundMap;
        for (int testCase = 0; testCase < T; testCase++) {
            soundMap = new HashMap<>();
            String recordedSound = br.readLine();

            while(true) {
                String sentence = br.readLine();
                if(sentence.equals(END)) break;
                String[] curSounds = sentence.split(" ");
                String animalSound = curSounds[2];
                if(!soundMap.containsKey(animalSound)) {
                    soundMap.put(animalSound, 0);
                }
            }

            String[] splitedSound = recordedSound.split(" ");
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < splitedSound.length; i++) {
                String cur = splitedSound[i];
                if(!soundMap.containsKey(cur)) {
                    ans.append(cur).append(" ");
                }
            }
            ans.delete(ans.length()-1,ans.length());
            if(testCase != T-1) {
                ans.append('\n');
            }
            bw.write(ans.toString());
        }
        bw.flush();bw.close();
    }
}