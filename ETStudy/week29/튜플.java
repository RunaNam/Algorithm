package week29;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution3 {
    public int[] solution(String s) {
        String regex = "[{},]";

        // 개수 값으로 묶어서 map으로 만듬
        Map<Integer, Long> collect = Arrays.stream(s.split(regex))
                .filter(a -> !a.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // value으로 정렬해서 array로 만듬
        return collect.entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .mapToInt(Entry::getKey)
                .toArray();

    }
}