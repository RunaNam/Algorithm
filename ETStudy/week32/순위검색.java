package week32;

import java.util.*;

class Solution2 {
    static Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] infos, String[] query) {
        int[] answer = new int[query.length];

        // 1. info 모든 경우의 수 map에 저장
        setInfo(infos);

        // 2. map에 저장된 점수 list 오름차순으로 정렬
        List<String> list = new ArrayList<>(map.keySet());
        for(int i=0; i<list.size(); i++) {
            List<Integer> scoreList = map.get(list.get(i));
            Collections.sort(scoreList);
        }

        // 3. 이분탐색 진행
        for (int i = 0; i < query.length; i++) {
            String str = query[i].replace(" and ", "");
            String[] tmp = str.split(" ");

            answer[i] = binarySearch(tmp[0], Integer.parseInt(tmp[1]));
        }

        return answer;
    }

    void setInfo(String[] infos) {
        for (String info : infos) {
            dfs("", 0, info.split(" "));
        }
    }

    void dfs(String str, int depth, String[] arr) {
        if (depth == 4) {
            int score = Integer.parseInt(arr[4]);

            if (map.containsKey(str)) {
                map.get(str).add(score);
            }

            else {
                List<Integer>tmp = new ArrayList<>();
                tmp.add(score);
                map.put(str, tmp);
            }
            return;
        }
        dfs(str + "-", depth + 1, arr);
        dfs(str + arr[depth], depth + 1, arr);
    }

    int binarySearch(String query, int score) {
        if (!map.containsKey(query)) {
            return 0;
        }

        List<Integer> tmpList = map.get(query);
        int start = 0;
        int end = tmpList.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (score > tmpList.get(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return tmpList.size() - start;
    }
}