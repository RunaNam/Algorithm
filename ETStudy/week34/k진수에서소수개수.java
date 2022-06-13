package week34;

import java.util.Arrays;

class Solution2 {
    public int solution(int n, int k) {
        String numberString = Integer.toString(n, k);
        return (int) Arrays.stream(numberString.split("0"))
                .filter(str -> !str.isEmpty())
                .mapToLong(Long::parseLong)
                .filter(num -> num > 1)
                .filter(this::isPrime)
                .count();
    }

    private boolean isPrime(long num) {
        if (num == 2) {
            return true;
        }

        for (int i = 2; (long) i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
