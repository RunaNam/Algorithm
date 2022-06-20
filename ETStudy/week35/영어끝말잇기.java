package week35;

import java.util.HashSet;
import java.util.Set;

class Solution2 {
    Set<String> log = new HashSet<>();

    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        int result = checkWords(words);
        if (result == 0) {
            return answer;
        }

        int order = result % n;
        int count = result / n + 1;
        if (order == 0) {
            order = n;
            count--;
        }

        answer[0] = order;
        answer[1] = count;
        return answer;
    }

    private int checkWords(String[] words) {
        String wordFirst;
        String wordSecond = words[0];
        log.add(wordSecond);

        for (int i = 1; i < words.length; i++) {
            wordFirst = wordSecond;
            wordSecond = words[i];

            //앞뒤글자 끝이 맞지 않는 경우
            if (wordFirst.charAt(wordFirst.length() - 1) != wordSecond.charAt(0)) {
                return i + 1;
            }

            //이미 있는 단어인 경우
            if (log.contains(wordSecond)) {
                return i + 1;
            }
            log.add(wordSecond);
        }
        return 0;
    }
}