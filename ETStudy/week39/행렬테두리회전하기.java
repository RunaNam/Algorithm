package week39;

class Solution {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {

		int[] solution = solution(3, 3, new int[][] {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}});
		for (int i : solution) {
			System.out.println(i);
		}
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];

		int[][] map = new int[rows][columns];
		int cnt = 1;
		int idx = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = cnt++;
			}
		}

		for (int[] query : queries) {
			answer[idx++] = spin(map, query);
		}

		return answer;
	}

	private static int spin(int[][] map, int[] query) {

		int min = Integer.MAX_VALUE;
		int x1 = query[0] - 1;
		int y1 = query[1] - 1;
		int x2 = query[2] - 1;
		int y2 = query[3] - 1;

		int tmp = map[x1][y1];
		int dir = 0;

		int curX = x1;
		int curY = y1 ;

		while (dir < 4) {
			int nextX = curX + dx[dir];
			int nextY = curY + dy[dir];

			// 범위를 넘어가면 회전함.
			if (nextX < x1 || nextX > x2 || nextY < y1 || nextY > y2) {
				dir++;
			} else {
				map[curX][curY] = map[nextX][nextY];
				min = Math.min(min, map[curX][curY]);
				curX = nextX;
				curY = nextY;
			}
		}

		map[curX][curY + 1] = tmp;

		min = Math.min(min, map[curX][curY + 1]);

		return min;	}
}