import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N;

    static int[][] map;
    static boolean[][] visited;

    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve(t);
        }
    }

    static boolean isValid(int r, int c) {
        return (r >= 0 && r < N) && (c >= 0 && c < N);
    }

    static void solve(int t) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            String rowString = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = rowString.charAt(c) == '.' ? 0 : -1;
                visited[r][c] = false;
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != -1) continue;

                // 현재 칸이 지뢰면 내 주변에 지뢰가 아닌 8칸에 1씩 더함.
                for (int i = 0; i < dx.length; i++) {
                    int nx = c + dx[i];
                    int ny = r + dy[i];
                    if (isValid(nx, ny) && map[ny][nx] != -1) {
                        map[ny][nx]++;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        int clickCount = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int current = map[r][c];
                if (current == 0 && !visited[r][c]) {
                    visited[r][c] = true;
                    queue.add(new int[]{ c, r });
                    bfs(queue);
                    clickCount++;
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int current = map[r][c];
                if (current > 0 && !visited[r][c]) {
                    clickCount++;
                }
            }
        }

        System.out.printf("#%d %d\n", t, clickCount);
    }

    static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] currentPos = queue.poll();
            int x = currentPos[0];
            int y = currentPos[1];

            if (map[y][x] > 0) continue;

            // 0인 경우 주변 8칸을 같이 터뜨림
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isValid(nx, ny)) continue;
                int neighbor = map[ny][nx];
                if (neighbor >= 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
