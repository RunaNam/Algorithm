package week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class J15559 {

    static int N, M;
    static int[][] map;
    static int[][] isVisited;
    static int answer = 0;
    static int num = 1;

    static int[] dx = {-1, 0, 0, 1}; // NWES
    static int[] dy = {0, -1, 1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                switch (c) {
                    case 'N':
                        map[i][j] = 0;
                        break;
                    case 'W':
                        map[i][j] = 1;
                        break;
                    case 'E':
                        map[i][j] = 2;
                        break;
                    case 'S':
                        map[i][j] = 3;
                        break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isVisited[i][j] == 0) {
                    dfs(i, j, num);
                    num++;
                }
            }
        }

        System.out.println(answer);

    }

    private static void dfs(int x, int y, int num) {
        isVisited[x][y] = num;

        int pos = map[x][y];
        int nx = x + dx[pos];
        int ny = y + dy[pos];

        if (isVisited[nx][ny] == 0) {
            dfs(nx, ny, num);
        }
        else if (isVisited[nx][ny] == num) // cycle이 만들어짐.
        {
            answer++;
        }
    }
}
