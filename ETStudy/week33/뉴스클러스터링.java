package week33;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Solution2 {

    public static void main(String[] args) {
        System.out.println(solution("aa1+aa2", "AAAA12"));
    }


    public static int solution(String str1, String str2) {
        Map<String, Integer> str1Map = new HashMap<>();
        Map<String, Integer> str2Map = new HashMap<>();

        int a = 0; // 교집합
        int b = 0; // 합집합

        setMap(str1, str1Map);

        setMap(str2, str2Map);


        for (Entry<String, Integer> entry : str1Map.entrySet()) {
            Integer num = str2Map.getOrDefault(entry.getKey(), 0);
            a += Math.min(num, entry.getValue());
            b += Math.max(num, entry.getValue());
        }

        for (Entry<String, Integer> entry : str2Map.entrySet()) {
            if(!str1Map.containsKey(entry.getKey())){
                b += entry.getValue();
            }
        }

        double answer = (double) a / (double) b;

        return (int)(answer * 65536);
    }

    private static void setMap(String str, Map<String, Integer> map) {
        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i+1);

            if(Character.isLetter(c1) && Character.isLetter(c2)){
                String s = str.substring(i, i + 2).toLowerCase();
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
    }
}