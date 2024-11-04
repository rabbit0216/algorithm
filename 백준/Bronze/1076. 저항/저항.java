import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static class Color {
        String value;
        long mulValue;

        public Color(String value, long mulValue) {
            this.value = value;
            this.mulValue = mulValue;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("black", new Color("0",1));
        colorMap.put("brown", new Color("1",10));
        colorMap.put("red", new Color("2",100));
        colorMap.put("orange", new Color("3",1_000));
        colorMap.put("yellow", new Color("4",10_000));
        colorMap.put("green", new Color("5",100_000));
        colorMap.put("blue", new Color("6",1_000_000));
        colorMap.put("violet", new Color("7",10_000_000));
        colorMap.put("grey", new Color("8",100_000_000));
        colorMap.put("white", new Color("9",1_000_000_000));

        String[] colors = new String[3];
        for (int i = 0; i < 3; i++) {
            colors[i] = br.readLine();
        }

        long ans = Long.parseLong(colorMap.get(colors[0]).value + colorMap.get(colors[1]).value) * colorMap.get(colors[2]).mulValue;
        bw.write(ans+""); bw.flush(); bw.close();

    }
}