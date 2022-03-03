import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/2206">벽 부수고 이동하기</a>
 */
public class Main {
    static int n;
    static int m;

    static int[][] map;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = Integer.MAX_VALUE;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[2][n][m];

        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
                visited[0][i][j] = Integer.MAX_VALUE;
                visited[1][i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        for (int i = 0; i < 2; i++) {
            answer = Math.min(answer, visited[i][n - 1][m - 1]);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void bfs() {
        int[] dx = {0, 1, 0, -1, 0, 2, 0, -2};
        int[] dy = {1, 0, -1, 0, 2, 0, -2, 0};

        Queue<Node> queue = new LinkedList<>();
        visited[0][0][0] = 1;
        visited[1][0][0] = 1;
        queue.offer(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 8; i++) {
                int xx = dx[i] + current.x;
                int yy = dy[i] + current.y;
                int breakCount = i < 4
                        ? current.breakCount
                        : current.breakCount + 1;

                if (xx < 0 || yy < 0 || xx >= n || yy >= m || breakCount > 1) {
                    continue;
                }
                if (visited[breakCount][xx][yy] != Integer.MAX_VALUE || map[xx][yy] == 1) {
                    continue;
                }
                if (i > 3 && map[xx - dx[i - 4]][yy - dy[i - 4]] == 0) {
                    continue;
                }

                visited[breakCount][xx][yy] = i < 4
                        ? visited[current.breakCount][current.x][current.y] + 1
                        : visited[current.breakCount][current.x][current.y] + 2;
                queue.offer(new Node(xx, yy, breakCount));
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int breakCount;

        public Node(int x, int y, int breakCount) {
            this.x = x;
            this.y = y;
            this.breakCount = breakCount;
        }
    }
}