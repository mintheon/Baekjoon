import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1629">곱셈</a>
 */
public class Main {
    static long a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long answer = pow(a, b);

        System.out.println(answer);
    }

    private static long pow(long number, long exp) {
        if (exp == 1) {
            return number % c;
        }

        long temp = pow(number, exp / 2);

        if (exp % 2 == 1) {
            return (temp * temp % c) * number % c;
        }

        return temp * temp % c;
    }
}