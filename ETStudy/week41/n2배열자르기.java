package week41;

class Solution {
	public int[] solution(int n, long left, long right) {
		int[] answer = new int[(int)(right - left + 1)];


		for (long i = left; i <= right; i++) {
			int r = (int)(left / n); // 몫 -> 가로줄
			int c = (int)(left % n); // 나머지 -> 세로줄

			if (c - r < 1) {
				answer[(int)(i - left)] = 1 + r;
			} else {
				answer[(int)(i - left)] = 1 + r + c - r;
			}

		}

		return answer;
	}
}