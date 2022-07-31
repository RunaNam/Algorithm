package week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main2 {

	static class Conveyor {
		int num;
		boolean robot;

		public Conveyor(int num, boolean robot) {
			this.num = num;
			this.robot = robot;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 컨베이어 밸트의 길이
		int K = Integer.parseInt(st.nextToken()); // 내구도 확인 개수

		Conveyor[] conveyors = new Conveyor[2 * N];
		Conveyor[] conveyors_copy = new Conveyor[2 * N];

		int zeroConveyor = 0;
		int cnt = 0;

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N * 2; i++) {
			conveyors[i] = new Conveyor(Integer.parseInt(st.nextToken()), false);
			conveyors_copy[i] = conveyors[i];
		}

		while (zeroConveyor < K) {
			// 컨베이어 이동
			for (int i = 1; i < N * 2; i++) {
				conveyors[i] = conveyors_copy[i - 1];
			}
			conveyors[0] = conveyors_copy[2 * N - 1];

			// n번째에 로봇 있으면 제거
			if (conveyors[N - 1].robot) {
				conveyors[N - 1].robot = false;
			}

			// 가장 먼저 올라간 로봇부터 벨트가 회전하는 방향으로 이동할 수 있다면 이동
			for (int i = N - 2; i >= 0; i--) {
				if (conveyors[i].robot && !conveyors[i + 1].robot && conveyors[i + 1].num >= 1) {
					conveyors[i].robot = false;
					conveyors[i + 1].robot = true;
					conveyors[i + 1].num -= 1;
					if (conveyors[i + 1].num == 0) {
						zeroConveyor++;
					}
				}
			}

			// n번째에 로봇 있으면 제거
			if (conveyors[N - 1].robot) {
				conveyors[N - 1].robot = false;
			}

			// 올리는 위치에 있는 칸의 내구도가 0이 아니고 로봇이 없으면
			if (conveyors[0].num >= 1 && !conveyors[0].robot) {
				// 로봇을 올린다.
				conveyors[0].robot = true;
				conveyors[0].num -= 1;
				if (conveyors[0].num == 0) {
					zeroConveyor++;
				}
			}

			for (int i = 0; i < 2 * N; i++) {
				conveyors_copy[i] = conveyors[i];
			}

			cnt++;
		}
		System.out.println(cnt);

	}
}





