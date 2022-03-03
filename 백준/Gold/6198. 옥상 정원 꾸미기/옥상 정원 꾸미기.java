import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @see <a href="boj.kr/6198">옥상 정원 꾸미기</a>
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long answer = 0;
        Stack<Long> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());

        long building = 0;
        for (int i = 0; i < n; i++) {
            building = Long.parseLong(br.readLine());

            while (!stack.isEmpty() && building >= stack.peek()) {
                stack.pop();
            }

            answer += stack.size();
            stack.push(building);
        }

        System.out.println(answer);
    }
}