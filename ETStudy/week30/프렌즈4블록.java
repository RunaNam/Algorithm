package week30;
import java.util.*;

class Solution2 {
    Character DELETE = 'X';
    int answer = 0;
    int globalM, globalN;

    Character[][] newBoard;
    boolean [][] visited;


    int[] dx = {1, 0, 1};
    int[] dy = {1, 1, 0};


    public int solution(int m, int n, String[] board) {
        globalM = m;
        globalN = n;

        //초기화
        newBoard = new Character[m][n];
        for(int i = 0; i<m; i++){
            for(int j = 0; j< n; j++){
                newBoard[i][j] =  board[i].charAt(j) ;
            }
        }

        while(true){
            visited = new boolean[m][n];

            for(int i = 0; i<m-1; i++){
                for(int j = 0; j< n-1; j++){
                    if(newBoard[i][j]  != DELETE && checkLetter(i, j, newBoard[i][j])){
                        // 체크
                        visited[i][j] = true;
                        visited[i+1][j] = true;
                        visited[i][j+1] = true;
                        visited[i+1][j+1] = true;
                    }
                }
            }

            int resultOfThisTurn = deleteBlock(visited);
            answer += resultOfThisTurn;

            if(resultOfThisTurn == 0){
                break;
            }
        }

        return answer;
    }

    boolean checkLetter(int x, int y, Character letter){
        for(int i = 0; i<3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(newBoard[nx][ny] != letter){
                return false;
            }
        }
        return true;
    }

    int deleteBlock(boolean[][] visited){
        int blocks = 0;

        for(int i = 0; i<globalM; i++){
            for(int j = 0; j< globalN; j++){
                if(visited[i][j]){
                    newBoard[i][j] = DELETE;
                    for(int k = i; k >= 1;k--){
                        newBoard[k][j] = newBoard[k-1][j];
                        newBoard[k-1][j] = DELETE;
                    }

                    blocks ++;
                }
            }
        }
        return blocks;
    }
}