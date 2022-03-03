import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1759">암호 만들기</a>
 */
public class Main {
    static final String VOWELS = "aeiou";

    static int l, c;
    static String[] inputs;
    static String[] texts;
    static boolean[] isVisited;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        inputs = new String[c];
        isVisited = new boolean[c];
        texts = new String[l];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            inputs[i] = st.nextToken();
        }

        Arrays.sort(inputs);

        dfs(0, 0, 0, 0);

        System.out.println(sb);
    }

    private static void dfs(int start, int depth, int vowelCount, int consCount) {
        if (l == depth) {
            if (vowelCount >= 1 && consCount >= 2) {
                for (int i = 0; i < l; i++) {
                    sb.append(texts[i]);
                }
                sb.append("\n");
            }

            return;
        }

        for (int i = start; i < c; i++) {
            if (isVisited[i]) {
                continue;
            }

            boolean isVowel = VOWELS.indexOf(inputs[i]) != -1;

            if (isVowel) {
                vowelCount++;
            } else {
                consCount++;
            }

            isVisited[i] = true;
            texts[depth] = inputs[i];
            dfs(i + 1, depth + 1, vowelCount, consCount);
            isVisited[i] = false;

            if (isVowel) {
                vowelCount--;
            } else {
                consCount--;
            }
        }
    }
}