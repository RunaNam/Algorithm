package week34;

import java.util.*;

class Solution3 {
    static int row;
    static int col;

    static Set<String> keys = new HashSet<>();

    public static void main(String[] args) {
        String[][] tmp = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};
        System.out.println(solution(tmp));
    }

    public static int solution(String[][] relation) {
        row = relation.length;
        col = relation[0].length;

        for (int i = 1; i <= col; i++) {
            boolean[] visited = new boolean[col];
            checkColumns(0, 0, i, relation, visited);
        }

        return keys.size();
    }

    private static void checkColumns(int idx, int depth, int size, String[][] relation, boolean[] visited) {
        if (depth == size) {
            String cols = "";
            for (int i = 0; i < col; i++) {
                if (visited[i]) {
                    cols += i;
                }
            }
            if (checkUniqueness(visited, relation) && checkMinimality(cols)) {
                keys.add(cols);
                System.out.println(cols);
            }
            return;
        }
        if (idx >= row) {
            return;
        }

        visited[idx] = true;
        checkColumns(idx + 1, depth + 1, size, relation, visited);

        visited[idx] = false;
        checkColumns(idx + 1, depth, size, relation, visited);
    }

    private static boolean checkUniqueness(boolean[] visited, String[][] relation) {
        Set<String> set = new HashSet<>();

        for (String[] tuple : relation) {
            String tmp = "";
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    tmp += tuple[i];
                }
            }
            set.add(tmp);
        }

        return set.size() == row;
    }

    private static boolean checkMinimality(String cols) {
        return keys.stream()
                .noneMatch(key -> cols.contains(key));
    }
}