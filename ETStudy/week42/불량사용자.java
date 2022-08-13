package week42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {

        int solution = solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc" },
            new String[] {"fr*d*", "*rodo", "******", "******" });
        System.out.println(solution);
    }

    static Map<String, List<String>> possibleBannedUser = new HashMap<>();
    static Set<String> answer = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        //map에 각 banned_id마다 가능한 유저값 넣어줌.
        for (String bannedId : banned_id) {
            String finalBannedId = bannedId.replace("*", ".");

            List<String> collect = Arrays.stream(user_id)
                .filter(id -> id.matches(finalBannedId))
                .collect(Collectors.toList());

            possibleBannedUser.put(bannedId, collect);
        }

        getBannedUser(0, banned_id, new ArrayList<>());

        return answer.size();
    }

    private static void getBannedUser(int idx, String[] banned_id, List<String> result) {
        if (idx == banned_id.length) {
            Collections.sort(result);
            answer.add(String.join("", result));
            return;
        }

        List<String> users = possibleBannedUser.get(banned_id[idx]);
        for (String user : users) {
            if(!result.contains(user)){
                result.add(user);
                getBannedUser(idx + 1, banned_id, result);
                result.remove(user);
            }
        }
    }

}