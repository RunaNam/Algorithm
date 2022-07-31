package week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	// 동 서 북 남
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	static int[] dice = new int[6];

	static int up = 1;
	static int left = 0;
	static int right = 2;
	static int forward = 5;
	static int backward = 3;
	static int down = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

/*
	주사위 모양

	      (상)
	(좌)  1 2 3 (우)
	 	   4
	       5 (하)
	       6
*/

		// 지도의 값을 넣음
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령의 리스트를 받음.
		int[] operations = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		for (int operation : operations) {
			// 만약 지도의 바깥으로 이동하면 무시한다.
			int nx = x + dx[operation - 1];
			int ny = y + dy[operation - 1];

			if (nx >= N || nx < 0 || ny >= M || ny < 0) {
				continue;
			}

			// 주사위 굴림
			rollDice(operation);

			// 이동한 칸에 있는 수가 0이면
			if (map[nx][ny] == 0) {
				// 주사위의 바닥면에 있는 수가 칸에 복사
				map[nx][ny] = dice[down];
			} else {
				// 이동한 칸에 있는 수가 0이 아니면

				// 칸에 쓰여 있는 수가 주사위의 바닥 면으로 복사
				dice[down] = map[nx][ny];
				// 칸에 쓰인 수는 0이 된다.
				map[nx][ny] = 0;
			}

			// 주사위 윗면의 수를 출력
			System.out.println(dice[up]);
			x = nx;
			y = ny;
		}
	}

	private static void rollDice(int operation) {
		int tmp = up;
		switch (operation) {
			case 1:
				// 동
				up = left;
				left = down;
				down = right;
				right = tmp;
				break;
			case 2:
				//서
				up = right;
				right = down;
				down = left;
				left = tmp;
				break;
			case 3:
				//북
				up = forward;
				forward = down;
				down = backward;
				backward = tmp;
				break;
			case 4:
				// 남
				up = backward;
				backward = down;
				down = forward;
				forward = tmp;
				break;
		}

	}
}
