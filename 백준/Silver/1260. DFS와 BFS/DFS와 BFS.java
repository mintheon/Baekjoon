import java.util.*;

class Dfs {
    private int n, m, v;
    private int map[][];
    private boolean visitedArr[];

    public Dfs(int n, int m, int v) {
        this.n = n;
        this.m = m;
        this.v = v;
        this.map = new int[n + 1][n + 1];
        this.visitedArr = new boolean[n + 1];
    }

    public void addEdge(int x, int y) {
        this.map[x][y] = this.map[y][x] = 1;
    }

    public void search(int idx) {
        this.visitedArr[idx] = true;
        System.out.print(idx + " ");

        for(int i = 1; i <= this.n; i++) {
            if(map[idx][i] == 1 && !visitedArr[i]) {
                search(i);
            }
        }
    }
}

class Bfs {
    private int n, m, v;
    private int map[][];
    private boolean visitedArr[];
    private Queue<Integer> queue;

    public Bfs(int n, int m, int v) {
        this.n = n;
        this.m = m;
        this.v = v;
        map = new int[n + 1][n + 1];
        visitedArr = new boolean[n + 1];
        queue = new LinkedList<>();
    }

    public void addEdge(int x, int y) {
        map[x][y] = map[y][x] = 1;
    }

    public void search(int idx) {
        int temp;

        queue.add(idx);
        visitedArr[idx] = true;

        while(!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp + " ");

            for(int i = 1; i <= n; i++) {
                if(map[temp][i] == 1 && !visitedArr[i]) {
                    queue.add(i);
                    visitedArr[i] = true;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int n, m, v;
        int startNode, endNode;
        
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        v = input.nextInt();

        Dfs dfs = new Dfs(n, m, v);
        Bfs bfs = new Bfs(n, m, v);

        for(int i = 0; i < m; i++) {
            startNode = input.nextInt();
            endNode = input.nextInt();
            dfs.addEdge(startNode, endNode);
            bfs.addEdge(startNode, endNode);
        }

        dfs.search(v);
        System.out.println();
        bfs.search(v);
    }
}
