import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int R,C,M;
    public static ArrayList<Shark> sharks = new ArrayList<>();

    public static int ans;
    public static int[][] move = {{0,0},{0,-1},{0,1},{1,0},{-1,0}};

    public static class Shark implements Comparable<Shark>{
        int r,c;
        int speed;
        int dir;
        int size;

        public Shark(int r, int c, int speed, int dir, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }


        @Override
        public int compareTo(Shark o) {
            return Integer.compare(this.size, o.size) * -1;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", speed=" + speed +
                    ", dir=" + dir +
                    ", size=" + size +
                    '}';
        }
    }

    public static class MapInfo{
        ArrayList<Shark> existSharks;
        MapInfo(){
            existSharks = new ArrayList<>();
        }

        MapInfo(ArrayList<Shark> existSharks){
            this.existSharks = existSharks;
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //
        MapInfo[][] map = new MapInfo[R][C];
        
        init(map);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r,c,speed,dir,size);
            sharks.add(shark);
            map[r][c].existSharks.add(shark); //상어 인덱스 저장
        }

        /*
        낚시왕이 오른쪽으로 한 칸 이동한다.
        낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
        상어가 이동한다.
         */
        ans = 0;
        for(int kingPlace = 0; kingPlace < C; kingPlace++){
            //땅에서 가까운 상어 잡기
            for(int r=0;r<R;r++){
                if(!map[r][kingPlace].existSharks.isEmpty()){
                    Shark shark = map[r][kingPlace].existSharks.get(0);
                    ans += shark.size;
                    sharks.remove(shark);
                    map[r][kingPlace].existSharks.remove(0);
                    break;
                }
            }

            //상어 이동
            int moveCnt = 0;
                for(int idx=0; idx<sharks.size();idx++){
                    Shark shark = sharks.get(idx);
                    int dc = move[shark.dir][0];
                    int dr = move[shark.dir][1];
                    int curR = shark.r;
                    int curC = shark.c;
                    map[curR][curC].existSharks.remove(shark);
                    int speed = shark.speed;
                    
                    if(shark.dir > 2) {
                    	speed =speed%(2*C-2);
                    } else {
                    	speed =speed%(2*R-2);
                    }
                    
                    int nr = curR;
                    int nc = curC;
                    boolean isFirst = true;
                    while(speed > 0){
                        nr += dr;
                        nc += dc;
                        if(isFirst && outScope(nr,nc,shark.dir)) {
                    		//처음 위치가 벽일 경우 이동 취소 및 방향 전환
                        	nr-=dr;
                        	nc-=dc;
                    		dr *= -1;
                            dc *= -1;
                            //상어 방향 전환해서 저장
                            for(int i=1; i<=4; i++) {
                                if (dr == move[i][1] && dc == move[i][0]) {
                                    shark.dir = i;
                                }
                            }
                            isFirst = false;
                            continue;
                    	}
                        
                        if(changeDir(nr,nc,shark.dir)){
                            dr *= -1;
                            dc *= -1;
                            //상어 방향 전환해서 저장
                            for(int i=1; i<=4; i++) {
                                if (dr == move[i][1] && dc == move[i][0]) {
                                    shark.dir = i;
                                }
                            }
                        }
                        isFirst = false;
                        speed--;
                    }
                    shark.r = nr;
                    shark.c = nc;

                    sharks.set(idx, shark);
                    map[nr][nc].existSharks.add(shark);
            }

            //한 칸에 상어 2마리 이상이면 가장 큰 상어만 남기기
            for(int r=0;r<R;r++){
                for(int c=0;c<C;c++){
                    if(map[r][c].existSharks.size() > 1){
                        Collections.sort(map[r][c].existSharks);
                        Shark tmp = map[r][c].existSharks.get(0); 
                        map[r][c].existSharks.remove(tmp);
                        for(Shark shark : map[r][c].existSharks) {
                        	if(sharks.contains(shark)) {
                        		sharks.remove(shark);
                        	}
                        }
                        map[r][c].existSharks.clear();
                        map[r][c].existSharks.add(tmp);
                    }
                }
            }
        }
        System.out.println(ans);

    }

    public static boolean scope(int r, int c){
        return r<0 || r>=R || c<0 || c>=C;
    }

    public static void init(MapInfo[][] map){
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                map[r][c] = new MapInfo();
            }
        }
    }

    public static boolean changeDir(int r,int c,int dir){
        if(dir>2){
            //가로
            return c == 0 || c == C - 1;
        }
        else{
            //세로
            return r == 0 || r == R - 1;
        }
    }
    
    public static boolean outScope(int r, int c, int dir) {
    	if(dir>2){
            //가로
            return c < 0 || c == C;
        }
        else{
            //세로
            return r < 0 || r == R;
        }
    }
}
