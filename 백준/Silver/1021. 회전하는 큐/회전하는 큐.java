import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/1021">회전하는 큐</a>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        LinkedList<Integer> dequeue = new LinkedList<>();

        int moveCount = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++) {
            dequeue.add(i);
        }

        int number, size, index;
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < m; i++) {
            size = dequeue.size();
            number = Integer.parseInt(st.nextToken());
            index = dequeue.indexOf(number);

            if (index == 0) {
            } else if ((size / 2) >= index) {
                for(int j = 0; j < index; j++) {
                    dequeue.offerLast(dequeue.pollFirst());
                    moveCount++;
                }
            } else {
                for(int j = 0; j < size - index; j++) {
                    dequeue.offerFirst(dequeue.pollLast());
                    moveCount++;
                }
            }

            dequeue.pollFirst();
        }

        System.out.println(moveCount);
    }
}