import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int R,C;
    public static int T;
    public static int[][] map;
    public static int[][] dustMove = {{1,0},{0,1},{-1,0},{0,-1}};
    public static Queue<Air> dustQ = new LinkedList<>();
    public static Air[] cleaner = new Air[2];

    public static class Air {
        int r,c;
        int dust;
        Air(int r, int c, int dust){
            this.r = r;
            this.c = c;
            this.dust = dust;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int airIdx = 0;
        for(int r=0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == -1){
                    cleaner[airIdx] = new Air(r,c,0);
                    airIdx++;
                }
                if(map[r][c] > 0){
                    dustQ.add(new Air(r,c,map[r][c]));
                }
            }
        }

        for(int time = 0; time < T; time++){
            diffuse();
            cleaning();
        }
        System.out.println(countDust());
    }

    public static boolean isScope(int r, int c){
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void diffuse(){
        int[][] tmp = copy(map);
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(map[r][c] > 4){
                    for(int dir=0;dir<4;dir++){
                        int dr = r + dustMove[dir][1];
                        int dc = c + dustMove[dir][0];
                        if(isScope(dr,dc)){
                            if(map[dr][dc] != -1){
                                tmp[dr][dc] += map[r][c]/5;
                                tmp[r][c] -= map[r][c]/5;
                            }
                        }
                    }
                }
            }
        }
        map = copy(tmp);
    }

    public static void cleaning(){
        Air airTop = cleaner[0];
        Air airBott = cleaner[1];

        int airR = airTop.r;
        int[][] copyMap = copy(map);

        //반시계
        copyMap[airR][1] = 0;
        for(int r=airR-1;r>0;r--){
            copyMap[r][0] = map[r-1][0];
        }
        for(int c=0;c<C-1;c++){
            copyMap[0][c] = map[0][c+1];
        }
        for(int r=0;r<airR;r++){
            copyMap[r][C-1] = map[r+1][C-1];
        }
        for(int c=C-1;c>1;c--){
            copyMap[airR][c] = map[airR][c-1];
        }

        //시계
        airR = airBott.r;
        copyMap[airR][1] = 0;
        for(int r=airR+1; r<R-1; r++){
            copyMap[r][0] = map[r+1][0];
        }
        for(int c=C-1;c>1;c--){
            copyMap[airR][c] = map[airR][c-1];
        }
        for(int r=R-1;r>airR;r--){
            copyMap[r][C-1] = map[r-1][C-1];
        }
        for(int c=0;c<C-1;c++){
            copyMap[R-1][c] = map[R-1][c+1];
        }
        map = copy(copyMap);
    }

    public static void print(int[][] map){
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                System.out.printf("%d ",map[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] copy(int[][] map){
        int[][] copied = new int[R][C];
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                copied[r][c] = map[r][c];
            }
        }
        return copied;
    }

    public static int countDust(){
        int sum = 0;
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(map[r][c] > 0){
                    sum+=map[r][c];
                }
            }
        }
        return sum;
    }
}
