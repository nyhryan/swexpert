import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int possibleCount;

    static boolean[] columnsTaken;
    /*
        r+c 값들을 보면 왼쪽 아래 대각선(/) 방향으로 같은 값들을 가짐 [최대 N * 2]
         r+c | 0 1 2 3
         --------------
           0 | 0 1 2 3
           1 | 1 2 3 4
           2 | 2 3 4 5
           3 | 3 4 5 6

           따라서 대각선 방향에 둘 수 없는지 보려면 rightDiagonalTaken[r+c]의 값을 체크한다.
           현재 셀: 0행 2열 -> r+c = 2
           왼쪽 아래 대각선 방향을 보면 죄다 값 2를 가진다. rightDiagonal[] = 2 인 칸은 스킵하면 된다.
     */
    static boolean[] rightDiagonalTaken;

    // 비슷하게 r-c 값들은 오른쪽 아래 대각선(\) 방향으로 같은 값들을 가짐 [-N 이상 N 이하]
    static boolean[] leftDiagonalTaken;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());

            columnsTaken = new boolean[N];
            rightDiagonalTaken = new boolean[N * 2];
            leftDiagonalTaken = new boolean[N * 2];
            possibleCount = 0;

            // 0행부터 시작
            // for c = 0..N
            // (0, 0)에 놓기 -> backtrack(1) [0행에 놓았으니 1행 체크]
            // (0, 1)에 놓기 -> backtrack(1)
            // ...
            backtrack(0);

            System.out.printf("#%d %d%n", t, possibleCount);
        }
    }

    static void backtrack(int r) {
        if (r == N) {
            possibleCount++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (columnsTaken[c] || leftDiagonalTaken[r + c] || rightDiagonalTaken[r - c + N - 1]) continue;

            // (r, c) 칸에 퀸을 놓았다고 가정
            columnsTaken[c] = leftDiagonalTaken[r + c] = rightDiagonalTaken[r - c + N - 1] = true;
            backtrack(r + 1);
            columnsTaken[c] = leftDiagonalTaken[r + c] = rightDiagonalTaken[r - c + N - 1] = false;
        }

    }
}
