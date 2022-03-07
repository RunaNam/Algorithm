package week26;

import java.util.*;

class Solution {

    static Map<Character, Integer> friends = new HashMap<Character, Integer>();
    static int[] list = new int[8]; // 알파벳 들어감
    static boolean[] visited = new boolean[8];

    static String[] s_data;

    static int answer = 0;

    public static void main(String[] args) {
        System.out.println(solution(2, new String[]{"N~F=0", "R~T>2"}));

    }

    public static int solution(int n, String[] data) {
        s_data = data;

        friends.put('A', 0);
        friends.put('C', 1);
        friends.put('F', 2);
        friends.put('J', 3);
        friends.put('M', 4);
        friends.put('N', 5);
        friends.put('R', 6);
        friends.put('T', 7);

        perm(0);

        return answer;
    }

    private static void perm(int cnt) {
        if (cnt == 8) {
            if (correct()) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            list[cnt] = i;
            perm(cnt + 1);
            visited[i] = false;
        }
    }

    private static boolean correct() {
        for (String condition : s_data) {
            int friend1 = list[friends.get(condition.charAt(0))];
            int friend2 = list[friends.get(condition.charAt(2))];

            int requireDis = Character.getNumericValue(condition.charAt(4)) + 1;
            int realDis = Math.abs(friend1 - friend2);

            if (condition.charAt(3) == '=') {
                if (requireDis != realDis) {
                    return false;
                }
            } else if (condition.charAt(3) == '>') {
                if (realDis <= requireDis) {
                    return false;
                }
            } else {
                if (realDis >= requireDis) {
                    return false;
                }
            }

        }
        return true;
    }

}