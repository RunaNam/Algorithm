package week27;

import java.util.*;

class Solution3 {

    static long answer = 0;

    static List<Long> operands = new ArrayList<>();
    static List<Character> operators = new ArrayList<>();

    static char[] calculateOrder = new char[3];
    static char[] operatorList = {'-', '*', '+'};
    static boolean[] visit = new boolean[3];

    public static void main(String[] args) {
        System.out.println(solution("50*6-3*2"));
    }

    public static long solution(String expression) {

        String num = "";
        for (int i = 0; i < expression.length(); i++) {
            char now = expression.charAt(i);
            if (now == '-' || now == '+' || now == '*') {
                operands.add(Long.parseLong(num));
                operators.add(expression.charAt(i));
                num = "";
            } else {
                num += now;
            }
        }
        operands.add(Long.parseLong(num));

        perm(0);

        return answer;
    }

    public static void perm(int cnt) {
        if (cnt == 3) {
            calculate();
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!visit[i]) {
                visit[i] = true;
                calculateOrder[cnt] = operatorList[i];
                perm(cnt + 1);
                visit[i] = false;
            }
        }
    }

    public static void calculate() {
        List<Character> operatorCopy = new ArrayList<>(operators);
        List<Long> operandCopy = new ArrayList<>(operands);

        for (int i = 0; i < 3; i++) {
            char calculateOperator = calculateOrder[i];

            for (int j = 0; j < operatorCopy.size(); j++) {
                char operatorNow = operatorCopy.get(j);
                if (operatorCopy.get(j) == calculateOperator) {
                    long num1 = operandCopy.get(j);
                    long num2 = operandCopy.get(j+1);

                    long sum = 0;
                    if (operatorNow == '+') {
                        sum = num1 + num2;
                    } else if (operatorNow == '-') {
                        sum = num1 - num2;
                    } else if (operatorNow == '*') {
                        sum = num1 * num2;
                    }

                    operandCopy.set(j, sum);
                    operandCopy.remove(j+1);
                    operatorCopy.remove(j);
                    j--;
                }
            }
        }
        answer = Math.max(answer, Math.abs(operandCopy.get(0)));

    }
}