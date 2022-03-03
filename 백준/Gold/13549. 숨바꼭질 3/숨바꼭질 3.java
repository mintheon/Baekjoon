import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/13549">숨바꼭질3</a>
 */
public class Main {
    static int n;
    static int k;

    static boolean visited[] = new boolean[100001];
    static int cost[] = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(cost[k]);
    }

    private static void bfs() {
        int[] dx = {-1, 1};
        int[] magicDx = {2};

        Queue<Integer> queue = new LinkedList<>();

        visited[n] = true;
        queue.offer(n);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == k) break;

            for (int i = 0; i < 1; i++) {
                int x = current * 2;

                if (x != 0) {
                    if (x < 0 || x >= 100001) {
                        continue;
                    }
                    if (visited[x]) {
                        continue;
                    }

                    visited[x] = true;
                    cost[x] = cost[current];
                    queue.offer(x);
                }
            }

            for (int i = 0; i < 2; i++) {
                int xx = dx[i] + current;

                if (xx < 0 || xx >= 100001) {
                    continue;
                }
                if (visited[xx]) {
                    continue;
                }

                visited[xx] = true;
                cost[xx] = cost[current] + 1;
                queue.offer(xx);
            }
        }
    }
}