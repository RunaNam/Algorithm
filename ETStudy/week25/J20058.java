package week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class J20058 {

    public static class Ice {

        int x, y;

        public Ice(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, POWN, Q;
    private static int[][] map;
    private static int[][] tempMap;
    private static int[] magics;
    private static boolean[][] visitied;

    private static int dx[] = {0, 0, -1, 1};
    private static int dy[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        POWN = (int) Math.pow(2, N);
        Q = Integer.parseInt(st.nextToken());

        map = new int[POWN + 1][POWN + 1];
        tempMap = new int[POWN + 1][POWN + 1];
        visitied = new boolean[POWN + 1][POWN + 1];
        magics = new int[Q];

        for (int i = 1; i <= POWN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= POWN; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            magics[i] = Integer.parseInt(st.nextToken());
        }

        for (int magicRange : magics) {
            fireStorm(magicRange);
        }

        System.out.println(countIce());
        System.out.println(largePiece());
    }

    private static int largePiece() {
        int largePiece = 0;
        for (int i = 1; i <= POWN; i++) {
            for (int j = 1; j <= POWN; j++) {
                if (!visitied[i][j] && map[i][j] > 0) {
                    largePiece = Math.max(largePiece, BFS(i, j));
                }
            }
        }
        return largePiece;
    }

    private static int BFS(int x, int y) {
        int pieces = 1;
        Queue<Ice> ices = new LinkedList<>();
        ices.add(new Ice(x, y));
        visitied[x][y] = true;

        while (!ices.isEmpty()) {
            Ice now = ices.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 < nx && nx <= POWN && 0 < ny && ny <= POWN) {
                    if (!visitied[nx][ny] && map[nx][ny] > 0) {
                        pieces++;
                        visitied[nx][ny] = true;
                        ices.add(new Ice(nx, ny));
                    }
                }
            }
        }

        return pieces;
    }

    private static void fireStorm(int magicRange) {
        int size = (int) (POWN / Math.pow(2, magicRange));
        int unit = POWN / size;

        for (int i = 1; i <= POWN; i += unit) {
            for (int j = 1; j <= POWN; j += unit) {
                spin(i, j, (int) Math.pow(2, magicRange));
            }
        }
        meltIce();
    }

    private static void meltIce() {
        boolean[][] meltCheck = new boolean[POWN + 1][POWN + 1];

        for (int i = 1; i <= POWN; i++) {
            for (int j = 1; j <= POWN; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (0 < nx && nx <= POWN && 0 < ny && ny <= POWN) {
                        if (map[nx][ny] > 0) {
                            cnt++;
                        }
                    }
                }

                // 3개 이하면 녹는것으로 판단.
                if (cnt < 3) {
                    meltCheck[i][j] = true;
                }
            }
        }

        //녹이는 과정
        for (int i = 1; i <= POWN; i++) {
            for (int j = 1; j <= POWN; j++) {
                if (meltCheck[i][j] && map[i][j] > 0) {
                    map[i][j]--;
                }
            }
        }
    }

    private static void spin(int x, int y, int magicRange) {
        //회전
        for (int i = 0; i < magicRange; ++i) {
            for (int j = 0; j < magicRange; ++j) {
                tempMap[j][magicRange - 1 - i] = map[x + i][y + j];
            }
        }

        //다시 저장
        for (int i = 0; i < magicRange; ++i) {
            for (int j = 0; j < magicRange; ++j) {
                map[x + i][y + j] = tempMap[i][j];
            }
        }
    }

    private static int countIce() {
        int result = 0;
        for (int i = 1; i <= POWN; i++) {
            result += Arrays.stream(map[i]).sum();
        }
        return result;
    }

}
