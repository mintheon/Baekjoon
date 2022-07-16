import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1926">그림</a>
 */
public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visit;

    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};

    static int pictureMax = 0;
    static int temp = 0;
    static int pictureCount = 0;

    public static void dfs(int x, int y) {
        visit[x][y] = true;
        pictureMax = Math.max(pictureMax, temp);

        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if (xx < 0 || yy < 0 || xx >= n || yy >= m || visit[xx][yy] || map[xx][yy] == 0)
                continue;

            temp++;
            dfs(xx, yy);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    temp = 1;
                    dfs(i, j);
                    pictureCount++;
                }
            }
        }

        System.out.println(pictureCount);
        System.out.println(pictureMax);
    }
}