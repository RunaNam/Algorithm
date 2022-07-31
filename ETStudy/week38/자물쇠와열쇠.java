package week38;

class Solution3 {
    public static void main(String[] args) {
        solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
    }

    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        for (int i = 0; i < 4; i++) {
            //1. 열쇠 돌리기
            key = spin(key);
            //2. key를 padding하기
            int padSize = lock.length - 1;
            int[][] paddedMap = pad(key, padSize);
            //3. padding한 map에 따라서 key가 맞는지 확인
            // paddedMap.length - padSize까지만 가는 이유는 해당 범위를 넘으면 lock이 pad된 map의 범위를 넘어가기 때문.
            for (int j = 0; j < paddedMap.length - padSize; j++) {
                for (int k = 0; k < paddedMap.length - padSize; k++) {
                    if (isRightKey(lock, paddedMap, j, k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isRightKey(int[][] lock, int[][] paddedMap, int j, int k) {
        for (int i = 0; i < lock.length; i++) {
            for (int l = 0; l < lock.length; l++) {
                // 해당 lock, paddedMap에서의 합이 1인지 확인.
                if (lock[i][l] + paddedMap[j + i][k + l] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] spin(int[][] lock) {
        int[][] spinnedLock = new int[lock.length][lock.length];

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                spinnedLock[i][j] = lock[lock.length - 1 - j][i];
            }
        }
        return spinnedLock;
    }

    private static int[][] pad(int[][] key, int padSize) {
        int[][] result = new int[key.length + padSize * 2][key.length + padSize * 2];
        for (int i = 0; i < key.length; i++) {
            System.arraycopy(key[i], 0, result[padSize + i], padSize + 0, key.length);
        }
        return result;
    }
}