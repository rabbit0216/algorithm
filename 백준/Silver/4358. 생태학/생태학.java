import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = "";
        TreeMap<String, Integer> dictionary = new TreeMap<>();
        int sum = 0;
        while ((input = br.readLine()) != null) {
            if (!dictionary.containsKey(input)) {
                dictionary.put(input, 1);
            } else {
                int value = dictionary.get(input);
                dictionary.put(input, value + 1);
            }
            sum++;
        }

        Iterator valueIter = dictionary.values().iterator();
        Iterator keyIter = dictionary.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (valueIter.hasNext()) {
            int value = (int) valueIter.next();
            String key = keyIter.next().toString();
            double calc = ((double) value / sum) * 100;
            double res = Math.round(calc * 10000) / 10000.0;
            sb.append(key).append(" ").append(String.format("%.4f", calc)).append('\n');
        }
        sb.delete(sb.length()-1, sb.length());
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}