package week33;

class Solution {
    boolean[] visited;
    int answer = -1;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        checkDungeon(dungeons, 0, k);

        return answer;
    }

    void checkDungeon(int[][] dungeons, int depth, int k) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                checkDungeon(dungeons, depth + 1, k - dungeons[i][1]);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, depth);
    }
}