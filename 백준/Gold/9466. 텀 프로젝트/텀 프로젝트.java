import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/9466">텀프로젝트</a>
 */
public class Main {
    static int n;
    static int[] studentWantToWorkWith;
    static boolean[] visited;
    static boolean[] done;

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            studentWantToWorkWith = new int[n + 1];
            visited = new boolean[n + 1];
            done = new boolean[n + 1];
            count = 0;

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                studentWantToWorkWith[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 1; j <= n; j++) {
                if (!done[j]) {
                    dfs(j);
                }
            }

            System.out.println(n - count);
        }
    }

    static public void dfs(int student) {
        if (visited[student]) {
            done[student] = true;
            count++;
        } else {
            visited[student] = true;
        }

        int next = studentWantToWorkWith[student];
        if (!done[next]) {
            dfs(next);
        }

        visited[student] = false;
        done[student] = true;
    }
}