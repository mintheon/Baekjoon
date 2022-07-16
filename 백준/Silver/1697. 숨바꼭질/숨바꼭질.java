import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1697">숨바꼭질</a>
 */
public class Main {
    static int map[] = new int[100001];
    static boolean visited[] = new boolean[100001];

    static int n;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dfs();
    }

    private static void dfs() {
        Queue<Integer> queue = new LinkedList();

        int[] dx = {-1, 1};

        visited[n] = true;
        queue.offer(n);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < 3; i++) {
                int xx = i == 2
                        ? current * 2
                        : current + dx[i];

                if (xx < 0 || xx > 100000) {
                    continue;
                }
                if (visited[xx]) {
                    continue;
                }

                visited[xx] = true;
                map[xx] = map[current] + 1;
                queue.offer(xx);
            }

            if (current == k) {
                System.out.println(map[current]);
                break;
            }
        }
    }
}