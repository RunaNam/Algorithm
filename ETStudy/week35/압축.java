package week35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    Map<String, Integer> dict = new HashMap<>();
    List<Integer> answer = new ArrayList<>();
    int lastIdx = 1;

    public int[] solution(String msg) {

        for (int i = 65; i < 91; i++) {
            char c = (char) i;
            dict.put(Character.toString(c), i - 64);
        }

        checkDict(msg.split(""));

        return answer.stream().mapToInt(a -> a).toArray();
    }

    private void checkDict(String[] splitMsg) {
        for (int i = 0; i < splitMsg.length; i++) {
            String now = splitMsg[i];

            while (i < splitMsg.length - 1 && find(now + splitMsg[i + 1])) {
                now += splitMsg[i + 1];
                i++;
            }

            answer.add(dict.get(now));
            if (i + 1 < splitMsg.length - 1) {
                dict.put(now + splitMsg[i + 1], dict.size() + 1);
            }
        }
    }

    private boolean find(String inputNow) {
        return dict.getOrDefault(inputNow, -1) != -1;
    }
}