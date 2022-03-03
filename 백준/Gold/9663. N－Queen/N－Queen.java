import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="boj.kr/9663">N-Queen</a>
 */
public class Main {
    static int n;
    static int board[];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n];

        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int depth) {
        if (n == depth) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            board[depth] = i;

            if (isValid(depth)) {
                dfs(depth + 1);
            }
        }
    }

    private static boolean isValid(int col) {
        for (int i = 0; i < col; i++) {
            if (board[col] == board[i]) {
                return false;
            }
            if (Math.abs(col - i) == Math.abs(board[col] - board[i])) {
                return false;
            }
        }

        return true;
    }
}