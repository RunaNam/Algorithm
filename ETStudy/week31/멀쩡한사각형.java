package week31;

class Solution {
    public long solution(int w, int h) {
        long totalPaper = ((long) w * (long) h);
        long gcd = getGcd(w, h);

        // 작은 사각형의 빈공간은 가로/gcd + 세로/gcd -1 임.
        // =  모서리를 지날 수 밖에 없음.

        // => (전체 타일 개수) - {(가로 / gcd) + (세로 / gcd) - 1} * gcd
        // => (전체 타일 개수) - {(가로 + 세로) / gcd - 1 } * gcd
        // => (가로 * 세로) - (가로 + 세로) + gcd

        return totalPaper - ((long) w + (long) h) + gcd;
    }


    public int getGcd(int n1, int n2) {
        if (n1 % n2 == 0) {
            return n2;
        }
        return getGcd(n2, n1 % n2);
    }
}