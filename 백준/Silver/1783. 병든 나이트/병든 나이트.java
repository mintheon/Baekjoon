import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, m;
        int moveCount = 0;
        Scanner input = new Scanner(System.in);

        n = input.nextInt();
        m = input.nextInt();

        if(n == 1) {

        } else if (n == 2) {
            moveCount = Math.min(3, (m - 1) / 2);
        } else if (m < 7) {
            moveCount = Math.min(3, m - 1);
        } else {
            moveCount = 4 + m - 7;
        }

        System.out.println(moveCount + 1);
    }
}
