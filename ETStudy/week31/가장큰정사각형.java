package week31;

class Solution3 {
    int w, h;

    public int solution(int[][] board) {
        w = board.length;
        h = board[0].length;
        int maxSquare = 1;

        if (isAllZero(board)) {
            return 0;
        }

        for (int i = 1; i < w; i++) {
            for (int j = 1; j < h; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = getSquareMinValue(board, i, j) + 1;
                    maxSquare = Math.max(maxSquare, board[i][j]);
                }
            }
        }

        return maxSquare * maxSquare;
    }

    boolean isAllZero(int[][] board) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    int getSquareMinValue(int[][] board, int x, int y) {
        return Math.min(board[x - 1][y], Math.min(board[x][y - 1], board[x - 1][y - 1]));
    }

}