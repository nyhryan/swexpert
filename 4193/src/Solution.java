import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T, N;
    static int[][] map;
    // (시간 % 3) 값이 같은 맵별로 방문여부를 체크하여 최단경로 탐색
    static boolean[][][] visited;
    static int[] start;
    static int[] end;

    // 상좌우하
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve(t);
        }
    }

    static int nextInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    static void solve(int t) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N][3];
        start = new int[2];
        end = new int[2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = new int[N];
            for (int j = 0; j < N; j++) {
                map[i][j] = nextInt(st);
            }

            visited[i] = new boolean[N][3];
        }

        st = new StringTokenizer(br.readLine());
        start[1] = nextInt(st);
        start[0] = nextInt(st);

        st = new StringTokenizer(br.readLine());
        end[1] = nextInt(st);
        end[0] = nextInt(st);
        // ---- end init ----

        // BFS 탐색으로 길 찾기
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

            // 도착지에 도착한 경우 탐색 종료
            if (x == end[0] && y == end[1]) {
                resultTime = time;
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 이미 방문한 칸이면 스킵
                if (visited[ny][nx][(time + 1) % 3]) continue;

                int neighbor = map[ny][nx];

                // 0(헤엄 가능)이거나
                // 2(소용돌이)이지만 지금 타이밍에 사라져서 밟을 수 있는 칸이 된 경우
                if (neighbor == 0 || (neighbor == 2 && time % 3 == 2)) {
                    visited[ny][nx][(time + 1) % 3] = true;
                    queue.add(new int[]{nx, ny, time + 1});
                }
                // 2(소용돌이)인 경우 제자리에서 기다려서 다음 번에 재조사
                else if (neighbor == 2 /* && time % 3 != 2 */) {
                    visited[y][x][(time + 1) % 3] = true;
                    queue.add(new int[]{x, y, time + 1});
                }
            }
        }

        System.out.printf("#%d %d\n", t, resultTime);
    }
}
