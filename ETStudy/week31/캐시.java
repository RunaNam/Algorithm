package week31;

import java.util.*;

class Solution2 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> cache = new ArrayDeque<>(cacheSize);

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        for (String city : cities) {
            String lowerCaseCity = city.toLowerCase();

            if (cache.contains(lowerCaseCity)) {
                cache.remove(lowerCaseCity);
                cache.add(lowerCaseCity);
                answer++;
            } else {
                if (cache.size() == cacheSize) {
                    cache.poll();
                }
                cache.add(lowerCaseCity);
                answer += 5;
            }
        }

        return answer;
    }
}