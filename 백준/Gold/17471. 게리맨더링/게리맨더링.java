import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[] people;
    static ArrayList<Integer>[] adj;
    static int minDiff;
    static Queue<Integer> checkedQueue;
    static Queue<Integer> uncheckedQueue;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        st = new StringTokenizer(br.readLine());
        people = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        minDiff = Integer.MAX_VALUE;
        makePowerSet(0, new boolean[N]);
        if (minDiff == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minDiff);

    }

    static void makePowerSet(int toCheck, boolean[] checked) {
        if (toCheck == checked.length) {
            int checkedSize = 0;
            int uncheckedSize = 0;
            boolean[] unchecked = new boolean[checked.length];
            boolean isAllFalse = true;
            boolean isAllTrue = true;
            //System.out.println(Arrays.toString(checked));

            for (int i = 0; i < checked.length; i++) {
                if (checked[i]) {
                    isAllFalse = false;
                    checkedSize++;
                } else {
                    isAllTrue = false;
                    unchecked[i] = true;
                    uncheckedSize++;
                }
            }
            if (isAllFalse || isAllTrue) {
                return;
            }
            if (!bfs(checked, checkedSize) || !bfs(unchecked, uncheckedSize)) {
                return;
            } else {
                int aPerson = calcPerson(checked);
                int bPerson = calcPerson(unchecked);
                int diff = Math.abs(aPerson - bPerson);
                minDiff = Math.min(diff, minDiff);
            }
            return;
        }

        checked[toCheck] = true;
        makePowerSet(toCheck + 1, checked);
        checked[toCheck] = false;
        makePowerSet(toCheck + 1, checked);
    }

    private static int calcPerson(boolean[] checked) {
        int sum = 0;
        for (int i = 0; i < checked.length; i++) {
            if (checked[i]) {
                sum += people[i + 1];
            }
        }
        return sum;
    }

    private static boolean bfs(boolean[] checked, int size) {
        boolean[] visited = new boolean[N + 1];
        int cnt = 1;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<checked.length;i++){
            if(checked[i]){
                queue.offer(i+1);
                visited[i+1] = true;
                break;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < N; i++) {
                if (checked[i] && !visited[i + 1] && adj[cur].contains(i + 1)) {
                    visited[i + 1] = true;
                    cnt++;
                    queue.offer(i+1);
                }
            }
        }
        //System.out.println("checked : " + Arrays.toString(checked) + " size : " + size + " cnt : " + cnt);
        return size == cnt;
    }
}