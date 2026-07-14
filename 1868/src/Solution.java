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
    
    // 8방향 검사 인덱스
    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve(t);
        }
    }

    // 배열 인덱스 검사
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

        // Step 1. 지뢰찾기 맵의 숫자를 일단 모두 계산
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

        // Step 2. 0인 칸들을 먼저 클릭하여 연쇄 반응으로 터뜨리고 총 클릭 횟수 구하기
        Queue<int[]> queue = new LinkedList<>();
        int clickCount = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int current = map[r][c];
                // 0인 칸은 주변 칸을 같이 터뜨리므로 BFS로 같이 터뜨리기
                if (current == 0 && !visited[r][c]) {
                    visited[r][c] = true;
                    queue.add(new int[]{ c, r });
                    bfs(queue);
                    clickCount++;
                }
            }
        }

        // Step 3. 1 이상인 칸들을 눌러가며 최종 클릭 횟수 구하기
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

            // 0이 아닌 경우 넘어가기
            if (map[y][x] > 0) continue;

            // 0인 경우 주변 8칸을 같이 터뜨림
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isValid(nx, ny)) continue;

                // 주변 8칸이 지뢰가 아니고 아직 방문하지 않은 경우 BFS로 탐색
                int neighbor = map[ny][nx];
                if (neighbor >= 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
