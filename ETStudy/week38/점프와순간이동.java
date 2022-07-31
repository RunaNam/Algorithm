package week38;

class Solution2 {
    public int solution(int n) {
        int ans = 0;

        while (0 < n) {
            if (n % 2 == 1) {
                n--;
                ans++;
            } else {
                n /= 2;
            }
        }

        return ans;
    }
}