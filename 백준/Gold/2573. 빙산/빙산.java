import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/2573">빙산</a>
 */
public class Main {
    static int n;
    static int m;

    static int[][] map;
    static int[][] afterMap;
    static boolean[][] visited;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int years = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        afterMap = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                afterMap[i][j] = map[i][j];
            }
        }

        int iceCount;
        boolean isAllMelted;

        do {
            isAllMelted = true;
            iceCount = 0;
            visited = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        parse(i, j);
                        iceCount++;
                        isAllMelted = false;
                    }
                }
            }

            if (iceCount >= 2) {
                System.out.println(years);
                return;
            }

            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0 && !visited[i][j]) {
                        meltIce(i, j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                map[i] = Arrays.copyOf(afterMap[i], m);
            }

            years++;
        } while (!isAllMelted);

        System.out.println(0);
    }

    public static void parse(int x, int y) {
        Queue<Node> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.offer(new Node(x, y));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx >= n || yy >= m) {
                    continue;
                }
                if (map[xx][yy] < 1 || visited[xx][yy]) {
                    continue;
                }

                visited[xx][yy] = true;
                queue.offer(new Node(xx, yy));
            }
        }
    }

    public static void meltIce(int x, int y) {
        Queue<Node> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.offer(new Node(x, y));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx >= n || yy >= m) {
                    continue;
                }
                if (visited[xx][yy]) {
                    continue;
                }
                if (map[xx][yy] > 0) {
                    afterMap[xx][yy] -= 1;
                    continue;
                }

                visited[xx][yy] = true;
                queue.offer(new Node(xx, yy));
            }
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}