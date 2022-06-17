package week34;

class Solution {
    public static void main(String[] args) {
        String solution = solution(2, 4, 2, 1);
        System.out.println(solution);
    }

    public static String solution(int n, int t, int m, int p) {
        String str = "";
        char[] answer = new char[t];
        int num = 0;

        while (str.length() < t * m) {
            str += Integer.toString(num++, n).toUpperCase();
        }

        for (int i = 0; i < t; i++) {
            answer[i] = str.charAt(p - 1 + (m * i));
        }

        return new String(answer);
    }
}