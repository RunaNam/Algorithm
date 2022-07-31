package week40;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	static class Position implements Comparable<Position> {
		int n1, n2, length;

		public Position(int n1, int n2) {
			this.n1 = n1;
			this.n2 = n2;
			length = n2 - n1;
		}

		@Override
		public int compareTo(Position position) {
			return Integer.compare(this.length, position.length);
		}

		@Override
		public String toString() {
			return "Position{" +
				"n1=" + n1 +
				", n2=" + n2 +
				", length=" + length +
				'}';
		}
	}

	static int gemCnt; // 구매해야하는 보석의 수

	static int[] answer =  {1, 1};
	static int gemLength = Integer.MAX_VALUE; //매장 길이


	//효율성 실패함. O(N2)라서 그런듯.
	public static void main(String[] args) {
		int[] solution = solution(new String[] {"A","B","B","B","B","B","B","C","B","A"});
		for (int i : solution) {
			System.out.println(i);
		}
	}

	public static int[] solution(String[] gems) {
		gemCnt = Arrays.stream(gems)
			.collect(Collectors.toSet())
			.size();

		for (int i = 0; i < (gems.length - gemCnt +1); i++) {
			check(gems, i);
		}

		return answer;
	}

	private static void check(String[] gems, int left) {
		int[] d = new int[gems.length];
		Set<String> gems_set = new HashSet<>();

		d[left] = 1;
		gems_set.add(gems[left]);

		for (int right = left; right < gems.length; right++) {
			d[right] = d[left];
			if (!gems_set.contains(gems[right])) {
				d[right]++;
				gems_set.add(gems[right]);
			}

			if (gems_set.size() == gemCnt) {
				if (gemLength > right - left) {
					answer[0] = left + 1;
					answer[1] = right + 1;
					gemLength = right - left;
					return;
				}
			}
		}
	}
}