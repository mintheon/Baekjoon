import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see <a href="boj.kr/1941">소문난 칠공주</a>
 */
public class Main {
    static final int SIZE = 5;

    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    static int answer;

    static int[] selected;
    static String[][] map;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        selected = new int[7];
        map = new String[SIZE][SIZE];
        isVisited = new boolean[SIZE * SIZE];

        for (int i = 0; i < SIZE; i++) {
            map[i] = br.readLine().split("");
        }

        searchPrincess(0, 0, 0);
        System.out.println(answer);
    }

    private static void searchPrincess(int start, int sCount, int depth) {
        if (depth == 7) {
            if (sCount >= 4 && isConnected()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < SIZE * SIZE; i++) {
            isVisited[i] = true;
            selected[depth] = i;

            if (map[i / SIZE][i % SIZE].equals("S")) {
                searchPrincess(i + 1, sCount + 1, depth + 1);
            } else {
                searchPrincess(i + 1, sCount, depth + 1);
            }

            isVisited[i] = false;
        }
    }

    private static boolean isConnected() {
        int count = 1;
        boolean isCheckVisited[] = new boolean[SIZE * SIZE];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(selected[0]);
        isCheckVisited[selected[0]] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = (current / SIZE) + DX[i];
                int yy = (current % SIZE) + DY[i];

                int index = (xx * SIZE) + yy;

                // 맵 범위 초과
                if (xx < 0 || yy < 0 || xx >= SIZE || yy >= SIZE) {
                    continue;
                }
                // 이미 방문
                if (isCheckVisited[index]) {
                    continue;
                }
                // 경로 벗어남
                if (!isVisited[index]) {
                    continue;
                }

                isCheckVisited[index] = true;
                queue.add(index);
                count++;
            }
        }

        return count == 7;
    }
}