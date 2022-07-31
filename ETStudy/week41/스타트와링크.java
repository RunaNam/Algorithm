package week41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 스타트와링크 {
	static int[][] map;
	static int N;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] teamCheck = new boolean[N];
		checkPermutation(0, 0, teamCheck);

		System.out.println(result);

	}

	private static void checkPermutation(int idx, int depth, boolean[] teamCheck) {
		if (depth == N / 2) {
			calculateScore(teamCheck);
		} else {
			for (int i = idx + 1; i < N; i++) {
				if (!teamCheck[i]) {
					teamCheck[i] = true;
					checkPermutation(i, depth + 1, teamCheck);
					teamCheck[i] = false;
				}
			}
		}
	}

	private static void calculateScore(boolean[] teamCheck) {
		List<Integer> team1 = new ArrayList<>();
		List<Integer> team2 = new ArrayList<>();

		int scoreGap = 0;

		for (int i = 0; i < N; i++) {
			if (teamCheck[i]) {
				team1.add(i);
			} else {
				team2.add(i);
			}
		}

		for (int n1 : team1) {
			for (int n2 : team1) {
				scoreGap += map[n1][n2];
			}
		}

		for (int n1 : team2) {
			for (int n2 : team2) {
				scoreGap -= map[n1][n2];
			}
		}

		result = Math.min(result, Math.abs(scoreGap));

	}

}