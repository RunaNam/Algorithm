package week26;

import java.util.*;
import java.util.Map.*;

class Solution2 {

    Map<String, Integer> menuMap = new HashMap<>();
    boolean visited[];

    public String[] solution(String[] orders, int[] courses) {
        List<String> answerList = new ArrayList<>();

        for (String order : orders) {
            for (int course : courses) {
                if (order.length() >= course) {
                    visited = new boolean[order.length()];
                    perm(order, course, "", 0, 0);
                }
            }
        }

        for (int course : courses) {
            int maxCount = 2;
            List<String> menuList = new ArrayList<>();

            for (Entry<String, Integer> menu : menuMap.entrySet()) {
                if (menu.getKey().length() == course && menu.getValue() >= maxCount) {
                    if (menu.getValue() > maxCount) {
                        menuList.clear();
                        maxCount = menu.getValue();
                    }
                    menuList.add(menu.getKey());
                }
            }
            answerList.addAll(menuList);
        }

        Collections.sort(answerList);
        return answerList.toArray(new String[0]);
    }

    public void perm(String order, int maxNum, String menu, int num, int idx) {
        if (num == maxNum) {
            char[] menuArr = menu.toCharArray();
            Arrays.sort(menuArr);
            String sortedMenu = new String(menuArr);

            menuMap.put(sortedMenu, menuMap.getOrDefault(sortedMenu, 0) + 1);
            return;
        }
        for (int i = idx; i < order.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm(order, maxNum, menu + order.charAt(i), num + 1, i);
                visited[i] = false;
            }
        }
    }
}
