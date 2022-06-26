package week38;

class Solution {
    public int solution(int n, int a, int b) {
        int answer = 1;

        // 앞뒤 순서를 확인 -> 뒤에서 +1 로 확인하기 위해서
        int firstParticipant = Math.min(a, b);
        int secondParticipant = Math.max(a, b);

        //앞의 조건 -> 4, 5였을 때에는 정답으로 나오면 안되므로 추가함.
        while (secondParticipant % 2 != 0 || firstParticipant + 1 != secondParticipant) {
            firstParticipant = runGame(firstParticipant);
            secondParticipant = runGame(secondParticipant);
            answer++;
        }

        return answer;
    }

    int runGame(int num) {
        if (num % 2 == 1) {
            return (num + 1) / 2;
        }
        return num / 2;
    }
}