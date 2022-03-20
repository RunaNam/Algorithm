package week27;

import java.util.*;
import java.util.stream.Collectors;

class Solution2 {

    static class Position {

        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }


    }

    public static void main(String[] args) {
        String[][] places = {
            {"OXPOO", "OPXOO", "OOOOO", "OOOOO", "OOOOO"},
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        System.out.println(Arrays.toString(solution(places)));
    }

    static List<Integer> answer = new ArrayList<>();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int[] solution(String[][] places) {
        for (String[] place : places) {
            checkPlace(place);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void checkPlace(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P' && isOverDistanceLimit(i, j, place)) {
                    answer.add(0);
                    return;
                }
            }
        }
        answer.add(1);
    }

    public static boolean isOverDistanceLimit(int x, int y, String[] place) {
        boolean[][] visit = new boolean[5][5];
        Queue<Position> q = new LinkedList<>();

        q.add(new Position(x, y));
        int distance = 0;

        while (!q.isEmpty() && distance < 2) {
            Position now = q.poll();
            visit[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && !visit[nx][ny]) {
                    if (place[nx].charAt(ny) == 'P') {
                        return true;
                    }
                    if (place[nx].charAt(ny) == 'O') {
                        visit[nx][ny] = true;
                        q.add(new Position(nx, ny));
                    }
                }
            }
            distance++;
        }
        return false;
    }
}