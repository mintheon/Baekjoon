import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="boj.kr/6593">상범빌딩</a>
 */
public class Main {
    static int l, r, c;
    static String[][][] building;
    static int[][][] cost;
    static boolean[][][] visited;
    static Node start;
    static Node end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            l = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) {
                break;
            }

            building = new String[l][c][r];
            cost = new int[l][c][r];
            visited = new boolean[l][c][r];

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    String[] inputs = br.readLine().split("");
                    for (int k = 0; k < r; k++) {
                        building[i][j][k] = inputs[k];

                        if ("S".equals(inputs[k])) {
                            start = new Node(i, j, k);
                        } else if ("E".equals(inputs[k])) {
                            end = new Node(i, j, k);
                        }
                    }
                }
                br.readLine();
            }

            bfs();

            if (cost[end.z][end.y][end.x] != 0) {
                System.out.println(String.format("Escaped in %d minute(s).", cost[end.z][end.y][end.x]));
            } else {
                System.out.println("Trapped!");
            }
        }
    }

    private static void bfs() {
        int[] dx = {0, 1, 0, -1, 0, 0};
        int[] dy = {1, 0, -1, 0, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        Queue<Node> queue = new LinkedList<>();
        visited[start.z][start.y][start.x] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 6; i++) {
                int xx = dx[i] + current.x;
                int yy = dy[i] + current.y;
                int zz = dz[i] + current.z;

                if (xx < 0 || yy < 0 || zz < 0 || zz >= l || yy >= c || xx >= r) {
                    continue;
                }
                if (visited[zz][yy][xx] || "#".equals(building[zz][yy][xx])) {
                    continue;
                }

                visited[zz][yy][xx] = true;
                cost[zz][yy][xx] = cost[current.z][current.y][current.x] + 1;
                queue.offer(new Node(zz, yy, xx));
            }
        }
    }

    private static class Node {
        int z;
        int y;
        int x;

        public Node(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
}