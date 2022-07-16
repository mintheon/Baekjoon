import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1600">말이 되고픈 원숭이</a>
 */

public class Main {
    static int k;
    static int w, h;

    static int[][] map;
    static int[][][] cost;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        cost = new int[k + 1][h][w];
        visited = new boolean[k + 1][h][w];

        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < h; j++) {
                for (int l = 0; l < w; l++) {
                    cost[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }
        cost[0][0][0] = 0;

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        parse();

        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            minCount = Math.min(minCount, cost[i][h - 1][w - 1]);
        }
        System.out.println(minCount == Integer.MAX_VALUE
                ? -1
                : minCount);
    }

    private static void parse() {
        Queue<Node> queue = new LinkedList<>();

        int dx[] = {-2, -2, -1, -1, 1, 1, 2, 2, 0, -1, 0, 1};
        int dy[] = {1, -1, 2, -2, 2, -2, 1, -1, 1, 0, -1, 0};

        for (int i = 0; i <= k; i++) {
            visited[i][0][0] = true;
        }
        queue.offer(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 12; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];
                int jumpCount = i < 8 ? current.jumpCount + 1 : current.jumpCount;

                if (xx < 0 || yy < 0 || xx >= w || yy >= h || jumpCount > k) {
                    continue;
                }
                if (visited[jumpCount][yy][xx] || map[yy][xx] == 1) {
                    continue;
                }

                visited[jumpCount][yy][xx] = true;
                cost[jumpCount][yy][xx] = current.cost + 1;
                queue.offer(new Node(xx, yy, cost[jumpCount][yy][xx], jumpCount));
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int cost;
        int jumpCount;

        public Node(int x, int y, int cost, int jumpCount) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.jumpCount = jumpCount;
        }
    }
}