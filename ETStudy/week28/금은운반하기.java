package week28;

class Solution3 {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        long start = 0;
        long end = (long) (10e14 * 3);

        answer = end;

        while (start <= end) {
            long mid = (start + end) / 2;
            if (check(a, b, g, s, w, t, mid)) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }

    public boolean check(int a, int b, int[] g, int[] s, int[] w, int[] t, long mid) {
        long gold = 0;
        long silver = 0;
        long total = 0;

        for (int i = 0; i < g.length; i++) {
            long time = t[i];
            long roundTime = time * 2;

            long moveCnt = mid / roundTime;
            if (mid % roundTime >= time) {
                moveCnt++;
            }

            long maxTake = w[i] * moveCnt; // 트럭이 한번에 옮길 수 있는 최대의 광물 양

            // 총량 비교 (최대값 넘지 않도록 maxTake랑 min으로 비교.)
            gold += Math.min((long) g[i], maxTake);
            silver += Math.min((long) s[i], maxTake);
            total += Math.min((long) g[i] + s[i], maxTake);
        }
        if (gold >= a && silver >= b && total >= a + b) {
            return true;
        }
        return false;
    }
}