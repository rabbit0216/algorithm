import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] graph;
    static StringBuilder sb;

    private static void cut(int r, int c, int size){
        int num = graph[r][c];
        boolean flag = false;

        outer:
        for(int y=r;y<r+size;y++){
            for(int x=c;x<c+size;x++){
                if(graph[y][x] != num){
                    flag = true;
                    break outer;
                }
            }
        }

        if(flag) {
            sb.append("(");
            int half = size / 2;
            cut(r, c, half);
            cut(r, c + half, half);
            cut(r + half, c, half);
            cut(r + half, c + half, half);
            sb.append(")");
        } else {
            sb.append(num);
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        graph = new int[N][N];

        for(int r=0;r<N;r++){
            String s = br.readLine();
            for(int c=0;c<N;c++){
                graph[r][c] = s.charAt(c) - '0';
            }
        }
        cut(0,0,N);
        System.out.println(sb);
    }
}