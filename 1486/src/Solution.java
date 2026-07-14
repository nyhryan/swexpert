import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int T, N, B;
	static int[] heights;
	static int minHeightSum;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			solve(t);
		}
	}
	
	static void solve(int t) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		heights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		minHeightSum = Integer.MAX_VALUE;
		// ---- end initialization ----
		
		// 0번째 인원부터 시작(0번째 인원을 넣거나/넣지 않거나 두 가지로 갈라짐)
		backtrack(0, 0);

		System.out.printf("#%d %d\n", t, minHeightSum - B);
	}
	
	static void backtrack(int i, int currentSum) {
		// 지금까지의 합이 정답이 될 수 있는 최솟값보다 큰 경우
		// 더 계산할 필요도 없이 되돌아가서 다른 경우의 수 찾기
		if (currentSum >= minHeightSum) return;
		
		// N번째 인원까지 넣거나 넣지 않으며 끝까지 온 경우
		if (i == N) {
			// 지금까지의 합이 필요한 높이(B) 이상인 경우 정답이 될 수 있는 값을 구함
			if (currentSum >= B) {
				// 최솟값을 갱신
				minHeightSum = Integer.min(currentSum, minHeightSum);
			}
			// 이전 경우의 수로 도르마무
			return;
		}

		// i번째 인원을 합에 포함하여 계산
		backtrack(i + 1, currentSum + heights[i]);

		// i번째 인원을 제외하고 합을 계산
		backtrack(i + 1, currentSum);
	}
}
