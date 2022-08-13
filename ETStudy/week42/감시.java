package week42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class CCTV {
        int x, y, dir, num;

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = 0;
        }
    }

    static int[][] officeMap;
    static List<CCTV> cctvs;

    static int N, M;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        officeMap = new int[N][M];
        cctvs = new ArrayList<>();

        int emptyPlace = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                officeMap[i][j] = tmp;
                if (0 < tmp && tmp < 6) {
                    cctvs.add(new CCTV(i, j, tmp));
                } else if (tmp == 0) {
                    emptyPlace++;
                }
            }
        }

        turnCCTV(0);
        System.out.println(emptyPlace - answer);

    }

    private static void turnCCTV(int idx) {
        if (idx == cctvs.size()) {
            // cctv 결과 확인해서 result 구함
            answer = Math.max(answer, checkCCTV());
            return;
        }
        CCTV cctv = cctvs.get(idx);
        // cctv 유형별로 체크해서 돌려봄.
        switch (cctv.num) {
            case 1:
            case 3:
            case 4:
                // 1, 3, 4는 모두 위를 중심으로 돌게.
                for (int i = 0; i < 4; i++) {
                    cctv.dir = i;
                    turnCCTV(idx + 1);
                }
                break;
            case 2:
                // 가로가 0, 세로가 1
                for (int i = 0; i < 2; i++) {
                    cctv.dir = i;
                    turnCCTV(idx + 1);
                }
                break;
            case 5:
                cctv.dir = 0;
                turnCCTV(idx + 1);
                break;
        }
    }

    private static int checkCCTV() {
        // 복사
        int[][] copyOffice = new int[N][M];
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyOffice[i][j] = officeMap[i][j];
            }
        }

        // cctv 번호에 따라서 체크
        for (CCTV cctv : cctvs) {
            if (cctv.num == 1) {
                result += check(copyOffice, cctv.x, cctv.y, cctv.dir);
            } else if (cctv.num == 2) {
                // 방향 1
                result += check(copyOffice, cctv.x, cctv.y, cctv.dir);
                // 방향 2
                result += check(copyOffice, cctv.x, cctv.y, cctv.dir + 2);
            } else if (cctv.num == 3) {
                // 방향 1
                result += check(copyOffice, cctv.x, cctv.y, cctv.dir);
                // 방향 2
                result += check(copyOffice, cctv.x, cctv.y, (cctv.dir + 1) % 4);
            } else if (cctv.num == 4) {
                // 방향 1
                result += check(copyOffice, cctv.x, cctv.y, cctv.dir);
                // 방향 2
                result += check(copyOffice, cctv.x, cctv.y, (cctv.dir + 1) % 4);
                // 방향 3
                result += check(copyOffice, cctv.x, cctv.y, (cctv.dir + 2) % 4);
            } else {
                result += check(copyOffice, cctv.x, cctv.y, cctv.dir);
                result += check(copyOffice, cctv.x, cctv.y, (cctv.dir + 1) % 4);
                result += check(copyOffice, cctv.x, cctv.y, (cctv.dir + 2) % 4);
                result += check(copyOffice, cctv.x, cctv.y, (cctv.dir + 3) % 4);
            }

        }
        return result;

    }

    private static int check(int[][] copyOffice, int x, int y, int dir) {
        int cnt = 0;
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // cctv 통과 가능한지 여부 생각 안했다가 틀림 ㅠ 안되는줄 알았지...
        while (0 <= nx && nx < N && 0 <= ny && ny < M && copyOffice[nx][ny] != 6){
            if (copyOffice[nx][ny] == 0) {
                copyOffice[nx][ny] = -1;
                cnt++;
            }

            nx += dx[dir];
            ny += dy[dir];
        }
        return cnt;
    }
}
