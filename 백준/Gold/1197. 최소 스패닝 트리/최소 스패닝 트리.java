import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int V, E;
    static ArrayList<Edge> edges;
    static int[] repres;

    static class Edge implements Comparable<Edge> {
        int start, end, edge;

        public Edge(int start, int end, int edge) {
            this.start = start;
            this.end = end;
            this.edge = edge;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.edge, o.edge);
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        repres = new int[V+1];

        for (int i = 0; i <= V; i++) {
            repres[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, edge));
        }

        Collections.sort(edges);

        int ans = 0;
        for (Edge e : edges) {
            int start = e.start; int end = e.end; int edge = e.edge;
            if(union(start, end)){
                ans += edge;
            }
        }

        bw.write(ans+""); bw.flush(); bw.close();
    }

    private static int findSet(int v) {
        if (v == repres[v]) return v;
        return repres[v] = findSet(repres[v]);
    }

    private static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB) return false;
        else {
            repres[rootA] = rootB;
            return true;
        }
    }
}