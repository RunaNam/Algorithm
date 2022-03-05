import java.util.*;

class Solution {

    public int solution(String s) {
        int answer = 1001;

        for (int i = 1; i <= s.length(); i++) {
            List<String> strings = new ArrayList<>();
            int j = 0;

            for (; j <= s.length() - i; j += i) {
                strings.add(s.substring(j, j + i));
            }

            if (j < s.length()) {
                strings.add(s.substring(j));
            }

            // print(strings);
            String oldS = strings.get(0);
            int count = 0;
            String newString = oldS;

            for (int k = 1; k < strings.size(); k++) {
                String newS = strings.get(k);

                if (oldS.equals(newS)) {
                    count++;
                } else {
                    if (count != 0) {
                        newString += count + 1;
                    }
                    newString += newS;
                    oldS = newS;
                    count = 0;
                }
            }

            if (count != 0) {
                newString += count + 1;
            }
            answer = Math.min(answer, newString.length());


        }

        return answer;
    }
}