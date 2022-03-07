import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1267">핸드폰 요금</a>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int time,
                y = 0,
                m = 0;
        for (int i = 0; i < n; i++) {
            time = Integer.parseInt(st.nextToken());
            y += ((time / 30) * 10) + (time / 30 == 0 && time % 30 == 0 ? 0 : 10);
            m += ((time / 60) * 15) + (time / 60 == 0 && time % 60 == 0 ? 0 : 15);
        }

        if (y == m) {
            System.out.println(String.format("Y M %d", y));
        } else if (y < m) {
            System.out.println(String.format("Y %d", y));
        } else {
            System.out.println(String.format("M %d", m));
        }
    }
}