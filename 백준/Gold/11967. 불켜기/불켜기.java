import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @see <a href="boj.kr/11967">불켜기</a>
 */
public class Main {
    static int n;
    static int m;

    static List<Node> room[][];
    static boolean light[][];
    static boolean visited[][];
    static boolean move[][];

    static int maxCount = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        room = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                room[i][j] = new ArrayList<>();
            }
        }

        light = new boolean[n][n];
        visited = new boolean[n][n];
        move = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int xx = Integer.parseInt(st.nextToken()) - 1;
            int yy = Integer.parseInt(st.nextToken()) - 1;

            room[x][y].add(new Node(xx, yy));
        }

        bfs();
        System.out.println(maxCount);
    }

    private static void bfs() {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));
        light[0][0] = visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx >= n || yy >= n) {
                    continue;
                }

                move[xx][yy] = true;
            }

            for (Node switches : room[current.x][current.y]) {
                if (!light[switches.x][switches.y]) {
                    light[switches.x][switches.y] = true;
                    maxCount++;
                }

                if (move[switches.x][switches.y] && !visited[switches.x][switches.y]) {
                    visited[switches.x][switches.y] = true;
                    queue.add(new Node(switches.x, switches.y));
                }
            }

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx >= n || yy >= n) {
                    continue;
                }
                if (visited[xx][yy] || !move[xx][yy] || !light[xx][yy]) {
                    continue;
                }

                visited[xx][yy] = true;
                queue.add(new Node(xx, yy));
            }
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}