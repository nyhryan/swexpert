import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T, N;
    static int[][] map;
    static boolean[][][] visited;
    static int[] start;
    static int[] end;

    static boolean isValid(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < N);
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve(t);
        }
    }

    static void solve(int t) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N][3];
        start = new int[2];
        end = new int[2];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            visited[i] = new boolean[N][3];
            for (boolean[] v: visited[i]) {
                Arrays.fill(v, false);
            }
        }

        int[] s = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] e = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        start[0] = s[1];
        start[1] = s[0];
        
        end[0] = e[1];
        end[1] = e[0];
        // ---- end init ----

        Queue<int[]> queue = new LinkedList<>();
        int sx = start[0], sy = start[1];
        visited[sy][sx][0] = true;
        
        queue.add(new int[]{ sx, sy, 0 });

        int resultTime = -1;
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int x = data[0];
            int y = data[1];
            int time = data[2];

            if (x == end[0] && y == end[1]) {
                resultTime = time;
                break;
            }

            // 상좌우하
            int[] dx = {0, -1, 1, 0};
            int[] dy = {-1, 0, 0, 1};

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isValid(nx, ny)) continue;
                if (visited[ny][nx][(time + 1) % 3]) continue;

                int neighbor = map[ny][nx];

                if (neighbor == 0 || (neighbor == 2 && time % 3 == 2)) {
                    visited[ny][nx][(time + 1) % 3] = true;
                    queue.add(new int[]{nx, ny, time + 1});
                }
                else if (neighbor == 2 /* && time % 3 != 2 */) {
                    visited[y][x][(time + 1) % 3] = true;
                    queue.add(new int[]{x, y, time + 1});
                }
            }
        }

        System.out.printf("#%d %d\n", t, resultTime);
    }
}
