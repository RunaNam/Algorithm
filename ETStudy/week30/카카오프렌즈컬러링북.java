package week30;

import java.util.*;

class Solution {
    class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;


    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    boolean[][] visited;
    int globalM, globalN;

    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        globalM = m;
        globalN = n;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    bfs(i, j, picture, picture[i][j]);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }


    void bfs(int x, int y, int[][] picture, int num) {
        Queue<Position> q = new LinkedList<>();
        int maxSize = 1;

        String a = "123";
        q.add(new Position(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {

            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (rightRange(nx, ny) && !visited[nx][ny] && picture[nx][ny] == num) {
                    visited[nx][ny] = true;
                    q.add(new Position(nx, ny));
                    maxSize++;
                }
            }
        }

        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, maxSize);
    }

    boolean rightRange(int nx, int ny) {
        return 0 <= nx && nx < globalM && 0 <= ny && ny < globalN;
    }

}
