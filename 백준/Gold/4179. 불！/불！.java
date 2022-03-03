import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/4179">불!</a>
 */
public class Main {
    static int r, c;

    static String[][] map;
    static int[][] fireCost;
    static int[][] jihoonCost;
    static boolean[][] visited;
    static Queue<Edge> jihoonQueue;
    static Queue<Edge> fireQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new String[r][c];
        fireCost = new int[r][c];
        jihoonCost = new int[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().split("");
        }

        dfs();
    }

    public static void dfs() {
        jihoonQueue = new LinkedList<>();
        fireQueue = new LinkedList<>();

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        findFire();

        while (!fireQueue.isEmpty()) {
            Edge current = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int rr = current.r + dr[i];
                int cc = current.c + dc[i];

                if (rr < 0 || cc < 0 || rr >= r || cc >= c) {
                    continue;
                }
                if (map[rr][cc].equals("#") || visited[rr][cc]) {
                    continue;
                }

                visited[rr][cc] = true;
                fireCost[rr][cc] = fireCost[current.r][current.c] + 1;
                fireQueue.add(new Edge(rr, cc));
            }
        }

        visited = new boolean[r][c];

        findJihoon();

        while (!jihoonQueue.isEmpty()) {
            Edge current = jihoonQueue.poll();
            visited[current.r][current.c] = true;

            for (int i = 0; i < 4; i++) {
                int rr = current.r + dr[i];
                int cc = current.c + dc[i];

                if (rr < 0 || cc < 0 || rr >= r || cc >= c) {
                    System.out.println(jihoonCost[current.r][current.c] + 1);
                    return;
                }
                if (fireCost[rr][cc] == -1 || (fireCost[rr][cc] != 0 && jihoonCost[current.r][current.c] + 1 >= fireCost[rr][cc])) {
                    continue;
                }
                if (map[rr][cc].equals("#") || visited[rr][cc]) {
                    continue;
                }

                visited[rr][cc] = true;
                jihoonCost[rr][cc] = jihoonCost[current.r][current.c] + 1;
                jihoonQueue.add(new Edge(rr, cc));
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    public static void findFire() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j].equals("F")) {
                    fireQueue.offer(new Edge(i, j));
                    visited[i][j] = true;
                    continue;
                }
            }
        }
    }

    public static void findJihoon() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j].equals("J")) {
                    jihoonQueue.offer(new Edge(i, j));
                    return;
                }
            }
        }
    }

    static class Edge {
        int r;
        int c;

        public Edge(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}