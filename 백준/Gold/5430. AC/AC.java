import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @see <a href="boj.kr/5430">AC</a>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<String> arr = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            arr.clear();

            String[] p = br.readLine().split("");

            int n = Integer.parseInt(br.readLine());

            String arrText = br.readLine();
            String[] texts = arrText.substring(1, arrText.length() - 1).split(",");

            for (int j = 0; j < n; j++) {
                arr.add(texts[j]);
            }

            boolean isReverse = false;
            boolean isError = false;
            for (int j = 0; j < p.length; j++) {
                if ("R".equals(p[j])) {
                    isReverse = !isReverse;
                } else {
                    if (arr.isEmpty()) {
                        isError = true;
                        break;
                    }

                    if (isReverse) {
                        arr.pollLast();
                    } else {
                        arr.pollFirst();
                    }
                }
            }

            if (isError) {
                sb.append("error\n");
            } else {
                sb.append("[");

                while (!arr.isEmpty()) {
                    if (isReverse) {
                        sb.append(arr.pollLast()).append(",");
                    } else {
                        sb.append(arr.pollFirst()).append(",");
                    }
                }

                if (sb.charAt(sb.length() - 1) == ',') {
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append("]\n");
            }
        }

        System.out.println(sb.toString());
    }
}