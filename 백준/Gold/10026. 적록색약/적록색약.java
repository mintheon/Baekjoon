import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/10026">적록색약</a>
 */
public class Main {
    static int n;
    static String[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new String[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().split("");
        }

        int generalCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, false);
                    generalCount++;
                }
            }
        }

        visited = new boolean[n][n];
        int weaknessCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, true);
                    weaknessCount++;
                }
            }
        }

        System.out.println(String.format("%d %d", generalCount, weaknessCount));
    }

    private static void dfs(int x, int y, boolean isWeakness) {
        Queue<Node> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.offer(new Node(x, y, map[x][y]));

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx >= n || yy >= n) {
                    continue;
                }
                if (visited[xx][yy]) {
                    continue;
                }
                if (isWeakness && (current.color.equals("R") || current.color.equals("G"))) {
                    if (map[xx][yy].equals("B")) {
                        continue;
                    }
                } else if (!current.color.equals(map[xx][yy])) {
                    continue;
                }

                visited[xx][yy] = true;
                queue.offer(new Node(xx, yy, map[xx][yy]));
            }
        }
    }

    static class Node {
        int x;
        int y;
        String color;

        public Node(int x, int y, String color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}