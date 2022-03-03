import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/2493">탑</a>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        Stack<Integer> indexStack = new Stack<>();
        Stack<Integer> topStack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        sb.append("0").append(" ");

        int top;

        topStack.push(Integer.parseInt(st.nextToken()));
        indexStack.push(1);

        for(int i = 2; i <= n; i++) {
            top = Integer.parseInt(st.nextToken());

            while(!topStack.isEmpty()) {
                if(top < topStack.peek()) {
                    break;
                }

                topStack.pop();
                indexStack.pop();
            }

            sb.append(indexStack.isEmpty() ? "0" : indexStack.peek()).append(" ");

            topStack.push(top);
            indexStack.push(i);
        }

        System.out.println(sb);
    }
}