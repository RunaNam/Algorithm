package week39;

class Solution2 {
	static int answer = Integer.MAX_VALUE;
	static int allCount = 0;// 카드의 개수 확인

	static boolean[][] visited = new boolean[4][4];

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) {
		int solution = solution(new int[][] {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0);
		System.out.println(solution);
	}

	public static int solution(int[][] board, int r, int c) {

		boolean cardCheck = false;

		for (int[] ints : board) {
			for (int anInt : ints) {
				if (anInt != 0) {
					allCount++;
				}
			}
		}

		//두쌍씩 있으므로 2로 나눔
		allCount /= 2;

		visited[r][c] = true;
		checkCardBoard(board, r, c, cardCheck, 0, 0, 0);

		return answer;
	}

	private static void checkCardBoard(int[][] board, int r, int c, boolean cardCheck, int oldCarNum, int move,
		int deletedCard) {
		// 지운 개수가 카드 개수랑 같으면 끝
		if (deletedCard == allCount) {
			System.out.println("~~" + move);
			answer = Math.min(answer, move);
			return;
		}

		// 만약 현재 위치가 0이 아니면 (카드 있음)
		if (board[r][c] != 0) {
			//이전에 잡은 카드가 없으면
			if (!cardCheck) {
				// 해당 카드를 체크하고, 기억해둠
				cardCheck = true;
				move++;
				oldCarNum = board[r][c];
			}
			// 이전에 잡은 카드가 있고 그 카드랑 같은 카드면
			else if (oldCarNum == board[r][c]) {
				// 기억한 카드를 지움
				cardCheck = false;
				oldCarNum = 0;
				move++;
				deletedCard++;
			}
		}

		// 이동
		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];

			// 한칸 이동 + 범위 넘어가면 무시
			if (checkPosition(nr, nc)) {
				visited[nr][nc] = true;
				checkCardBoard(board, nr, nc, cardCheck, oldCarNum, move + 1, deletedCard);
				visited[nr][nc] = false;
			}

			nr += dx[i];
			nc += dy[i];
			// 카드까지 이동
			while (checkPosition(nr, nc)) {
				if (board[nr][nc] != 0) {
					visited[nr][nc] = true;
					checkCardBoard(board, nr, nc, cardCheck, oldCarNum, move + 1, deletedCard);
					visited[nr][nc] = false;
					break;
				}
			}
		}

	}

	private static boolean checkPosition(int nr, int nc) {
		return 0 <= nr && nr < 4 && 0 <= nc && nc < 4 && !visited[nr][nc];
	}
}