package week28;

class Solution {

    public String solution(int n) {
        String[] one_two_four = {"4", "1", "2"};
        String answer = "";

        int num = n;

        while (num > 0) {
            int check = num % 3;
            num /= 3;

            if (check == 0) {
                num--;
            }

            answer = one_two_four[check] + answer;
        }

        return answer;
    }
}
