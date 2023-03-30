import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int[][] board;
    static ArrayList<Node> boardLine;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[10][10];
        boardLine = new ArrayList<>();
        visited = new boolean[10][10];
        int notZero = 0;
        for (int r = 1; r <= 9; r++) {
            String s = br.readLine();
            for (int c = 1; c <= 9; c++) {
                board[r][c] = s.charAt(c-1) - '0';
                if(board[r][c]==0){
                    boardLine.add(new Node(r,c));
                } else {
                    notZero++;
                }
            }
        }
        recur(0,notZero);
    }

    private static void recur(int line, int cnt){
        if(cnt == 81){
            for (int r = 1; r <= 9; r++) {
                for (int c = 1; c <= 9; c++) {
                    System.out.print(board[r][c]);
                }
                System.out.println();
            }
            System.exit(0);
        }

        for (int i = 1; i <= 9; i++) {
            int r = boardLine.get(line).r;
            int c = boardLine.get(line).c;
            if(findBox(i,r,c) && findSero(i, r, c) && findGaro(i,r,c)){
                board[r][c] = i;
                recur(line + 1, cnt + 1);
                board[r][c] = 0;
            }
        }

    }

    private static boolean findBox(int num, int R, int C) {
        int startR, startC, endR, endC;
        if(R <= 3){
            startR = 1;
            endR = 3;
        } else if(R <= 6) {
            startR = 4;
            endR = 6;
        } else {
            startR = 7;
            endR = 9;
        }
        if(C <= 3){
            startC = 1;
            endC = 3;
        } else if(C <= 6) {
            startC = 4;
            endC = 6;
        } else {
            startC = 7;
            endC = 9;
        }

        for (int r = startR; r <= endR; r++) {
            for (int c = startC; c <= endC ; c++) {
                if(board[r][c] == num){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean findSero(int num, int R, int C) {
        for (int r = 1; r <= 9; r++) {
            if(board[r][C] == num){
                return false;
            }
        }
        return true;
    }

    private static boolean findGaro(int num, int R, int C) {
        for (int c = 1; c <= 9; c++) {
            if(board[R][c] == num){
                return false;
            }
        }
        return true;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}