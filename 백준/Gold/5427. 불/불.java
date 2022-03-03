import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/5427">불</a>
 */
public class Main {
    static int t;
    static int h;
    static int w;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static String[][] map;
    static int[][] move;
    static int[][] fire;
    static boolean[][] fireVisited;
    static boolean[][] visited;

    static Queue<Node> queue;
    static Queue<Node> fireQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new String[h][w];
            move = new int[h][w];
            fire = new int[h][w];

            queue = new LinkedList<>();
            fireQueue = new LinkedList<>();

            fireVisited = new boolean[h][w];
            visited = new boolean[h][w];

            for (int j = 0; j < h; j++) {
                map[j] = br.readLine().split("");
                for (int k = 0; k < w; k++) {
                    if (map[j][k].equals("*")) {
                        fireVisited[j][k] = true;
                        fireQueue.add(new Node(j, k, 0));
                    } else if (map[j][k].equals("@")) {
                        visited[j][k] = true;
                        queue.add(new Node(j, k, 0));
                    }
                }
            }

            moveFire();
            move();
        }
    }

    public static void moveFire() {
        while (!fireQueue.isEmpty()) {
            Node current = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx >= h || yy >= w) {
                    continue;
                }
                if (map[xx][yy].equals("#") || fireVisited[xx][yy]) {
                    continue;
                }

                fireVisited[xx][yy] = true;
                fire[xx][yy] = current.second + 1;
                fireQueue.offer(new Node(xx, yy, current.second + 1));
            }
        }
    }

    public static void move() {
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = current.x + dx[i];
                int yy = current.y + dy[i];

                if (xx < 0 || yy < 0 || xx > h - 1 || yy > w - 1) {
                    System.out.println(current.second + 1);
                    return;
                }
                if (visited[xx][yy]) {
                    continue;
                }
                if (!map[xx][yy].equals(".") || (!(fire[xx][yy] == 0) && fire[xx][yy] <= current.second + 1)) {
                    continue;
                }

                visited[xx][yy] = true;
                queue.offer(new Node(xx, yy, current.second + 1));
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    static class Node {
        int x;
        int y;
        int second;

        public Node(int x, int y, int second) {
            this.x = x;
            this.y = y;
            this.second = second;
        }
    }
}