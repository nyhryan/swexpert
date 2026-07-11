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
		
		backtrack(0, 0);
		System.out.printf("#%d %d\n", t, minHeightSum - B);
	}
	
	static void backtrack(int i, int currentSum) {
		if (currentSum >= minHeightSum) return;
		
		if (i == N) {
			if (currentSum >= B) {
				minHeightSum = Integer.min(currentSum, minHeightSum);
			}
			return;
		}

		backtrack(i + 1, currentSum + heights[i]);
		backtrack(i + 1, currentSum);
	}
}
