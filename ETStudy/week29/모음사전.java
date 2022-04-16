package week29;

import java.util.*;

class Solution {
    int [] wordCount = {781, 156, 31, 6, 1};
    Map<Character, Integer> wordIdx = Map.of('A', 0, 'E',1, 'I',2, 'O',3, 'U',4);


    public int solution(String word) {
        int result = 0;

        for(int i = 0; i<word.length(); i++){
            result +=  wordCount[i] * wordIdx.get(word.charAt(i)) +1;
        }

        return result;
    }
}