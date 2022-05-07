package week32;

import java.util.*;

class Solution {
    int w, h;
    int answer = Integer.MAX_VALUE;

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    static class Pair {
        int x, y, dis;

        public Pair(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }


    public int solution(int[][] maps) {
        w = maps.length;
        h = maps[0].length;
        bfs(maps);
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        return answer;
    }

    void bfs(int[][] maps) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 1));

        boolean[][] check = new boolean[w][h];
        check[0][0] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.x == w - 1 && p.y == h - 1) {
                answer = Math.min(p.dis, answer);
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (0 <= nx && nx < w && 0 <= ny && ny < h) {
                    if (!check[nx][ny] && maps[nx][ny] == 1) {
                        check[nx][ny] = true;

                        q.add(new Pair(nx, ny, p.dis + 1));
                    }
                }
            }
        }

    }
}