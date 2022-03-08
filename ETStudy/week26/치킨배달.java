package week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static class Location {

        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static boolean[] visited;

    static List<Location> chickens = new ArrayList<>();
    static Location[] existChickens;
    static int result = Integer.MAX_VALUE;

    static List<Location> house = new ArrayList<>();

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    house.add(new Location(i, j));
                }
                if (num == 2) {
                    chickens.add(new Location(i, j));
                }
            }
        }

        visited = new boolean[chickens.size()];
        existChickens = new Location[M];

        perm(0, 0);
        System.out.println(result);
    }

    private static void perm(int idx, int count) {
        // 여기 인덱스 안넣고 했다가 진짜 시간초과 한 5번 났음 뭐가 잘못된건지 몰랐는데...
        if (count == M) {
            checkMin();
            return;
        }
        for (int i = idx; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                existChickens[count] = chickens.get(i);
                perm(i, count + 1);
                visited[i] = false;
            }
        }
    }

    private static void checkMin() {
        int distance = 0;
        for (Location location : house) {
            distance += checkDistance(location.x, location.y);
        }

        if (result > distance) {
            result = distance;
        }
    }


    private static int checkDistance(int x, int y) {
        int distance = Integer.MAX_VALUE;

        for (Location existChicken : existChickens) {
            int disNow = Math.abs(x - existChicken.x) + Math.abs(y - existChicken.y);

            if (distance > disNow) {
                distance = disNow;
            }
        }

        return distance;
    }


}
